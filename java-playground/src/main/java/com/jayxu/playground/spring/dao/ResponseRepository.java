package com.jayxu.playground.spring.dao;

import org.springframework.data.repository.CrudRepository;

import com.jayxu.playground.spring.model.Response;

public interface ResponseRepository extends CrudRepository<Response, Long> {

}
