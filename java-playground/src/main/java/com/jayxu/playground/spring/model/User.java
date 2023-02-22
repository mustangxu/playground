/**
 * Authored by jayxu @2021
 */
package com.jayxu.playground.spring.model;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;

import org.apache.commons.codec.digest.Md5Crypt;
import org.eclipse.persistence.annotations.Cache;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.github.javafaker.Faker;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.persistence.Version;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "users", indexes = @Index(columnList = "username"))
@Data
@NoArgsConstructor
// @Cacheable
@Cache
@EntityListeners(AuditingEntityListener.class)
public class User implements Serializable {
    @Serial
    private static final long serialVersionUID = -9020200257408632559L;
    @Id
    @GeneratedValue
    private Long id;
    @Column(length = 36, nullable = false)
    @NotBlank
    private String username;
    @Column(length = 48, nullable = false)
    @NotBlank
    private String password;
    private Long age;
    @Version
    private Integer version;
    @CreatedDate
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "create_time")
    private Date createTime;
    @LastModifiedDate
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "update_time")
    private Date updateTime;

    public User(Long id, String username, String password, Integer age) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.age = age == null ? null : (long) age;
    }

    public static User buildTestUser(long id) {
        var faker = new Faker();
        var ts = System.currentTimeMillis();

        return new User(id, faker.name().username(),
            Md5Crypt.md5Crypt(("" + ts).getBytes()), (int) ts % 100 + 1);
    }
}
