package com.jayxu.playground.spring.dao;

import java.util.UUID;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.github.javafaker.Faker;
import com.jayxu.playground.spring.model.User;

import lombok.extern.slf4j.XSlf4j;

@SpringBootTest
@XSlf4j
class UserRepositoryTest {
    @Autowired
    private UserRepository dao;

    @Test
    void testFindUsersByPassword() {
        var users = this.dao.findByPassword("PassWord");
        Assertions.assertEquals(0, users.size(), "count");
    }

    @Test
    void testUpdateUser() {
        this.dao.findById(1L).ifPresent(u -> {
            u.setAge(33);
            this.dao.save(u);
        });
    }

    @Test
    void testAddUsers() {
        var count = 1_000;
        var added = 0;
        var faker = new Faker();

        for (var i = 0; i < count; i++) {
            var user = new User(System.currentTimeMillis() + i,
                faker.name().username(), UUID.randomUUID().toString(),
                faker.number().numberBetween(1, 100));
            user = this.dao.save(user);
            UserRepositoryTest.log.info("{}", user);

            if (user != null) {
                added++;
            }
        }

        Assertions.assertEquals(count, added, "added");
    }
}
