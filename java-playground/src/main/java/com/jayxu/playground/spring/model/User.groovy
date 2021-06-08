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
import org.springframework.vault.repository.mapping.Secret

import groovy.transform.TupleConstructor

@Entity
@Secret
@Table(indexes = [ @Index(columnList = "username") ])
@TupleConstructor
public class User implements Serializable {
    @Id
    @org.springframework.data.annotation.Id
    Integer id
    @Column(length = 32, nullable = false)
    @NotBlank
    String username
    @Column(length = 48, nullable = false)
    @NotBlank
    String password
    @Type(type = "com.jayxu.playground.spring.model.AgeType")
    Long age

    //    public User(Integer id = null, String username = null, String password = null) {
    //        this.id = id
    //        this.username = username
    //        this.password = password
    //    }
}
