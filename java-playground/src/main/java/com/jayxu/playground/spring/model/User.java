/**
 * Authored by jayxu @2021
 */
package com.jayxu.playground.spring.model;

import java.io.Serializable;

import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.Table;
import javax.persistence.Version;
import javax.validation.constraints.NotBlank;

import lombok.Data;

@Entity
@Table(name = "users", indexes = @Index(columnList = "username"))
@Cacheable
@Data
//@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class User implements Serializable {
    private static final long serialVersionUID = -9020200257408632559L;
    @Id
    @org.springframework.data.annotation.Id
    private Long id;
    @Column(length = 36, nullable = false)
    @NotBlank
    private String username;
    @Column(length = 48, nullable = false)
    @NotBlank
    private String password;
    //    @Type(type = "com.jayxu.playground.spring.model.AgeType")
    private Integer age;
    @Version
    private Integer version;

    public User(Long id, String username, String password, Integer age) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.age = age;
    }
}
