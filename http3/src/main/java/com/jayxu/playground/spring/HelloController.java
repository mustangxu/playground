/**
 * Authored by jayxu-@2025
 */
package com.jayxu.playground.spring;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.ServletRequest;

@RestController
public class HelloController {
    @GetMapping("/")
    public String index(ServletRequest request) {
        return "Greetings from Spring Boot using " + request.getProtocol()
            + "!";
    }
}
