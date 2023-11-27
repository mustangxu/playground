/**
 * Authored by jayxu @2021
 */
package com.jayxu.playground.spring.service;

import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

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
 * @author jayxu
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
            log.info("User {} added", id);

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

    public long addUsers(int count, int batchSize) {
        var userMap = IntStream.range(0, count).parallel().mapToObj(i -> {
            var u = User.fake(null);
            u.setSequence(i);
            return u;
        }).collect(Collectors.groupingBy(u -> u.getSequence() / batchSize));

        userMap.forEach(
            (k, v) -> log.info("key: {}, size: {}", k, v.size()));

        return userMap.values().stream().map(this.dao::saveAll)
            .mapToLong(it -> it.spliterator().estimateSize()).sum();
    }
}
