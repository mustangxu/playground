/**
 * Authored by jayxu @2021
 */
package com.jayxu.playground.spring.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jayxu.playground.spring.dao.UserRepository;
import com.jayxu.playground.spring.model.User;

@Service
@Transactional
public class UserService {
    @Autowired
    private UserRepository dao;

    public User getOrAddUser(String id) {
        return this.dao.findById(id).orElseGet(() -> {
            var user = new User(id, "jayxu" + System.currentTimeMillis(),
                "password");
            return this.dao.save(user);
        });
    }
}
