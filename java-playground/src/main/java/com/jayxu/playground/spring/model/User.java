/**
 * Authored by jayxu @2021
 */
package com.jayxu.playground.spring.model;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.github.javafaker.Faker;

import jakarta.persistence.Cacheable;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.persistence.Transient;
import jakarta.persistence.Version;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.XSlf4j;

@Entity
@Table(name = "users", indexes = @Index(columnList = "username"))
@Data
@NoArgsConstructor
@Cacheable
@EntityListeners(AuditingEntityListener.class)
@XSlf4j
public class User implements Serializable {
    @Serial
    private static final long serialVersionUID = -9020200257408632559L;
    @Id
    @GeneratedValue
    private Long id;
    @Column(length = 36, nullable = false)
    @NotBlank
    private String username;
    @Column(length = 48)
    private String password;
    @Column(length = 256)
    @Email
    private String email;
    @OneToOne(cascade = CascadeType.ALL)
    private UserInfo userInfo;
    @Version
    private Integer version;
    @CreatedDate
    @Temporal(TemporalType.TIMESTAMP)
    private Date createTime;
    @LastModifiedDate
    @Temporal(TemporalType.TIMESTAMP)
    private Date updateTime;
    @Transient
    private Integer sequence;

    public User(Long id, String username, String password) {
        this.id = id;
        this.username = username;
        this.password = password;

        User.log.debug(this.toString());
    }

    public static User fake(Long id) {
        var faker = new Faker();

        var user = new User(id, faker.name().username(), faker.crypto().md5());
        user.setEmail(faker.internet().emailAddress());
        user.setUserInfo(UserInfo.fake());

        return user;
    }
}
