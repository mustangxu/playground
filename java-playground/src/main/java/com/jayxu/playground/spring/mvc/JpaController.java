/**
 * Authored by jayxu @2021
 */
package com.jayxu.playground.spring.mvc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jayxu.playground.spring.model.User;
import com.jayxu.playground.spring.service.UserService;

@RestController
public class JpaController {
    @Autowired
    private UserService service;

    @RequestMapping("/users/{id}")
    public User getUserById(@PathVariable int id) {
        return this.service.getOrAddUser(id);
    }
}
