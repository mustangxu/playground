/**
 * Authored by jayxu @2021
 */
package com.jayxu.playground.spring.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.jayxu.playground.spring.model.User;

public interface UserDAO extends PagingAndSortingRepository<User, Long>,
        CrudRepository<User, Long> {
    List<User> findByPassword(String password);
}
