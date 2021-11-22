/**
 * Authored by jayxu @2021
 */
package com.jayxu.playground.spring.dao;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import com.jayxu.playground.spring.model.User;

public interface UserRepository extends CrudRepository<User, Long> {
    List<User> findByPassword(String password);

    List<User> findByOrderById(Pageable page);
}
