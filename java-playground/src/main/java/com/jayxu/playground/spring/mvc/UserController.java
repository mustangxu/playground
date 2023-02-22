/**
 * Authored by jayxu @2021
 */
package com.jayxu.playground.spring.mvc;

import java.util.Optional;
import java.util.UUID;
import java.util.stream.IntStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.github.javafaker.Faker;
import com.jayxu.playground.spring.model.User;
import com.jayxu.playground.spring.service.UserService;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService service;

    @GetMapping("/{id}")
    public User getUserById(@PathVariable int id) {
        return this.service.getOrAddUser(id);
    }

    @GetMapping("/top")
    public Page<User>
            getTopNUsers(@RequestParam(defaultValue = "10") int size) {
        return this.service.getUsersPage(0, size, null);
    }

    @GetMapping("")
    public Page<User> getUsers(@RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(required = false) String orderby) {
        return this.service.getUsersPage(page, size, orderby);
    }

    @PostMapping("/add")
    public long addUsers(@RequestParam(defaultValue = "10") int count) {
        var faker = new Faker();

        var users = IntStream.range(0, count)
            .mapToObj(i -> new User(System.currentTimeMillis() + i,
                faker.name().username(), UUID.randomUUID().toString(),
                faker.number().numberBetween(1, 100)))
            .toList();
        return this.service.addUsers(users);
    }

    @PutMapping("/{id}")
    public Optional<User> updateUserPassword(@PathVariable long id,
            @RequestParam String password) {
        return this.service.updateUserPassword(id, password);
    }
}
