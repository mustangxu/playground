/// **
// * // * Copyright(c) 2010-2022 by Youxin Financial Group
// * // * All Rights Reserved
// * //
// */
// package com.jayxu.playground.spring.shell;
//
// import java.util.List;
// import java.util.UUID;
//
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.shell.standard.ShellComponent;
// import org.springframework.shell.standard.ShellMethod;
// import org.springframework.shell.standard.ShellOption;
//
// import com.github.javafaker.Faker;
// import com.jayxu.playground.spring.model.User;
// import com.jayxu.playground.spring.service.UserService;
//
/// **
// * @author jay
// */
// @ShellComponent
// public class UserShell {
// @Autowired
// private UserService service;
//
// @ShellMethod("Get user count")
// public long count() {
// return this.service.getCount();
// }
//
// @ShellMethod("Add users")
// public void addUsers(@ShellOption(defaultValue = "10") int count) {
// var faker = new Faker();
//
// for (var i = 0; i < count; i++) {
// var user = new User(System.currentTimeMillis() + i,
// faker.name().username(), UUID.randomUUID().toString(),
// faker.number().numberBetween(1, 100));
// this.service.addUser(user);
// }
// }
//
// @ShellMethod("Get top users")
// public List<User> getTopUsers(@ShellOption(defaultValue = "10") int size) {
// return this.service.getUsersPage(0, size, null).getContent();
// }
// }
