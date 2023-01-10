/**
 * Authored by jayxu @2021
 */
package com.jayxu.playground.spring.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.jayxu.playground.spring.dao.UserRepository;
import com.jayxu.playground.spring.model.User;

import jakarta.transaction.Transactional;
import lombok.extern.slf4j.XSlf4j;

/**
 * @author xujiajing
 */
@Service
@Transactional
@XSlf4j
public class UserService {
    @Autowired
    private UserRepository dao;

    @Cacheable("users")
    public User getOrAddUser(long id) {
        return this.dao.findById(id).orElseGet(() -> {
            var user = User.buildTestUser(id);
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

    public Page<User> getUsersPage(int page, int size, String orderby) {
        var p = PageRequest.of(page, size);
        if (orderby != null) {
            p = p.withSort(Sort.Direction.ASC, orderby);
        }

        return this.dao.findAll(p);
    }

    public long addUsers(Iterable<User> users) {
        return this.dao.saveAll(users).spliterator().estimateSize();
    }
}
