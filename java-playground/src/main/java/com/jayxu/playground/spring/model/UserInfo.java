/**
 * Authored by jayxu @2023
 */
package com.jayxu.playground.spring.model;

import java.util.Date;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.github.javafaker.Faker;
import com.jayxu.playground.util.RandomUtils;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.persistence.Version;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author xujiajing
 */
@Entity
@Table(name = "user_infos")
@Data
@NoArgsConstructor
// @Cacheable
@EntityListeners(AuditingEntityListener.class)
public class UserInfo {
    @Id
    @GeneratedValue
    private Long id;
    @Column(length = 1000)
    private String address;
    private Gender gender;
    private Integer age;
    @Version
    private Integer version;
    @CreatedDate
    @Temporal(TemporalType.TIMESTAMP)
    private Date createTime;
    @LastModifiedDate
    @Temporal(TemporalType.TIMESTAMP)
    private Date updateTime;

    public enum Gender {
        MALE,
        FEMALE
    }

    public static UserInfo build() {
        var faker = new Faker();

        var info = new UserInfo();
        info.setGender(RandomUtils.randomEnum(UserInfo.Gender.class));
        info.setAddress(faker.address().fullAddress());
        info.setAge(faker.number().numberBetween(1, 100));

        return info;
    }
}
