/**
 * Authored by jayxu @2023
 */
package com.jayxu.playground.spring.mvc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jayxu.playground.spring.model.Order;
import com.jayxu.playground.spring.service.OrderService;

/**
 * @author xujiajing
 */
@RestController
@RequestMapping("/orders")
public class OrderController {
    @Autowired
    private OrderService service;

    @GetMapping("/user")
    public Page<Order> getOrdersByUserId(@RequestParam long userId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        return this.service.getOrdersByUserId(userId, page, size);
    }

    @PostMapping("/fill")
    public void fillOrders(@RequestParam long userId,
            @RequestParam(defaultValue = "10") int size) {
        this.service.fillOrders(userId, size);
    }
}
