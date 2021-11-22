/**
 * Authored by jayxu @2021
 */
package com.jayxu.playground.spring.model

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Index
import javax.persistence.Table
import javax.validation.constraints.NotBlank

import org.hibernate.annotations.Type

@Entity
@Table(indexes = [ @Index(columnList = "username") ])
public class User implements Serializable {
    @Id
    @org.springframework.data.annotation.Id
    Long id
    @Column(length = 36, nullable = false)
    @NotBlank
    String username
    @Column(length = 48, nullable = false)
    @NotBlank
    String password
    @Type(type = "com.jayxu.playground.spring.model.AgeType")
    Integer age

    public User(Long id = null, String username = null, String password = null,
    Integer age = null) {
        this.id = id
        this.username = username
        this.password = password
        this.age = age
    }
}
