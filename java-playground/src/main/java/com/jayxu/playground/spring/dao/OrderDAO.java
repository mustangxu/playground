/**
 * Authored by jayxu @2023
 */
package com.jayxu.playground.spring.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.jayxu.playground.spring.model.Order;

/**
 * @author xujiajing
 */
public interface OrderDAO extends PagingAndSortingRepository<Order, Long>,
        CrudRepository<Order, Long> {
    Page<Order> findByUserId(long userId, Pageable page);
}