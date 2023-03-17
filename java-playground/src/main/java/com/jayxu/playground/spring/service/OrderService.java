/**
 * Authored by jayxu @2023
 */
package com.jayxu.playground.spring.service;

import java.util.stream.IntStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jayxu.playground.spring.dao.OrderDAO;
import com.jayxu.playground.spring.dao.UserDAO;
import com.jayxu.playground.spring.model.Order;
import com.jayxu.playground.spring.model.User;

/**
 * @author xujiajing
 */
@Service
@Transactional
public class OrderService {
    @Autowired
    private OrderDAO dao;
    @Autowired
    private UserDAO userDAO;

    public Page<Order> getOrdersByUserId(long userId, int page, int size) {
        var p = PageRequest.of(page, size, Direction.DESC, "createTime");

        return this.dao.findByUserId(userId, p);
    }

    public long fillOrders(long userId, int size) {
        var user = this.userDAO.findById(userId).orElse(null);
        if (user == null) {
            user = this.userDAO.save(User.fake(userId));
        }

        final var u = user;
        var orders = IntStream.range(0, size).mapToObj(i -> Order.fake(u))
            .toList();
        return this.dao.saveAll(orders).spliterator().getExactSizeIfKnown();
    }
}
