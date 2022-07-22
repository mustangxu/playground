package com.jayxu.playground.spring.dao;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

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
            u.setAge(33L);
            this.dao.save(u);
        });
    }

    @Test
    void testAddUsers() {
        var count = 1000;

        for (var i = 0; i < count; i++) {
            var user = User.buildTestUser(System.currentTimeMillis() + i);
            user = this.dao.save(user);
            UserRepositoryTest.log.info("{}", user);
        }
    }
}
