/**
 * Authored by jayxu @2021
 */
package com.jayxu.playground.spring.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.jayxu.playground.spring.model.User;

public interface UserRepository extends CrudRepository<User, Integer> {
    List<User> getUsersByPassword(String password);
}
