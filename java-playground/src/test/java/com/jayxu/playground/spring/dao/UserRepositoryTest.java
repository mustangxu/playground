package com.jayxu.playground.spring.dao;

import java.util.Random;
import java.util.UUID;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Pageable;

import com.jayxu.playground.spring.model.User;

@SpringBootTest
class UserRepositoryTest {
    @Autowired
    private UserRepository dao;

    @Test
    void testFindUsersByPassword() {
        var users = this.dao.findByPassword("PassWord");
        Assertions.assertEquals(1, users.size(), "count");
    }

//    @Test
    void testAddUsers() {
        var count = 1_000;
        var added = 0;

        for (var i = 0; i < count; i++) {
            var user = new User(System.currentTimeMillis() + i,
                UUID.randomUUID().toString(),
                UUID.randomUUID().toString(), new Random().nextInt(100));
            user = this.dao.save(user);

            if (user != null) {
                added++;
            }
        }

        Assertions.assertEquals(count, added, "added");
    }

    @Test
    void testFindTopByOrderById() {
        this.dao.findByOrderById(Pageable.ofSize(5)).stream()
            .map(User::getId)
            .forEach(System.out::println);
    }
}