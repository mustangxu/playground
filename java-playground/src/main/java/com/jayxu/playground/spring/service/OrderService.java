/**
 * Authored by jayxu @2023
 */
package com.jayxu.playground.spring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestParam;

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

    public Page<Order> getOrdersByUserId(@RequestParam long userId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        var p = PageRequest.of(page, size);

        return this.dao.findByUserId(userId, p);
    }

    public void fillOrders(long userId, int size) {
        if (!this.userDAO.existsById(userId)) {
            this.userDAO.save(User.buildTestUser(userId));
        }

        for (var i = 0; i < size; i++) {
            var order = Order.buildOrder(userId);
            this.dao.save(order);
        }
    }
}
