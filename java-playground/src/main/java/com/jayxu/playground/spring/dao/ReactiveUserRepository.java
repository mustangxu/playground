package com.jayxu.playground.spring.dao;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;

import com.jayxu.playground.spring.model.User;

public interface ReactiveUserRepository extends
        ReactiveCrudRepository<User, Long> {

}
