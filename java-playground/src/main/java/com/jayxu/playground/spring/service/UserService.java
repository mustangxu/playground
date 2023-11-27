/**
 * Authored by jayxu @2021
 */
package com.jayxu.playground.spring.service;

import java.util.Optional;
import java.util.stream.LongStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jayxu.playground.spring.dao.UserDAO;
import com.jayxu.playground.spring.model.User;

import lombok.extern.slf4j.XSlf4j;

/**
 * @author xujiajing
 */
@Service
@Transactional
@XSlf4j
public class UserService {
    @Autowired
    private UserDAO dao;

    @Cacheable("users")
    public User getOrAddUser(long id) {
        return this.dao.findById(id).orElseGet(() -> {
            var user = User.fake(id);
            user = this.dao.save(user);
            UserService.log.info("User {} added", id);

            return user;
        });
    }

    public long getCount() {
        return this.dao.count();
    }

    @CachePut(value = "users", key = "#p0")
    public Optional<User> updateUserPassword(long id, String password) {
        return this.dao.findById(id).map(u -> {
            u.setPassword(password);
            return this.dao.save(u);
        });
    }

    public Page<User> getUsersPage(int page, int size, Direction direction,
            String orderby) {
        var p = PageRequest.of(page, size);
        if (orderby != null) {
            p = p.withSort(direction, orderby);
        }

        return this.dao.findAll(p);
    }

    public long addUsers(int count) {
        var users = LongStream.range(0, count).parallel()
            .mapToObj(i -> User.fake(null)).toList();

        return this.dao.saveAll(users).spliterator().getExactSizeIfKnown();
    }
}
