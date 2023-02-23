/**
 * Authored by jayxu @2021
 */
package com.jayxu.playground.spring.mvc;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jayxu.playground.spring.dao.UserDAO;
import com.jayxu.playground.spring.model.User;
import com.jayxu.playground.spring.service.UserService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/users")
@Slf4j
public class UserController {
    @Autowired
    private UserService service;
    @Autowired
    private UserDAO dao;

    @GetMapping("/{id}")
    public User getUserById(@PathVariable int id) {
        var user = this.service.getOrAddUser(id);
        log.debug(user.toString());

        return user;
    }

    @GetMapping("/top")
    public Page<User>
            getTopNUsers(@RequestParam(defaultValue = "10") int size) {
        return this.service.getUsersPage(0, size, Direction.DESC, "createTime");
    }

    @GetMapping("")
    public Page<User> getUsers(@RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(required = false) String orderby) {
        return this.service.getUsersPage(page, size, Direction.ASC, orderby);
    }

    @PostMapping("/add")
    public long addUsers(@RequestParam(defaultValue = "10") int count) {
        return this.service.addUsers(count);
    }

    @PutMapping("/{id}")
    public Optional<User> updateUserPassword(@PathVariable long id,
            @RequestParam String password) {
        return this.service.updateUserPassword(id, password);
    }
}
