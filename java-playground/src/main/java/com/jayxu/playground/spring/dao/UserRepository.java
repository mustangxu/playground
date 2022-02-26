/**
 * Authored by jayxu @2021
 */
package com.jayxu.playground.spring.dao;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.jayxu.playground.spring.model.User;

public interface UserRepository extends PagingAndSortingRepository<User, Long> {
    List<User> findByPassword(String password);
}
