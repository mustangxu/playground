/**
 * Authored by jayxu @2021
 */
package com.jayxu.playground.spring.mvc;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;

/**
 * @author xujiajing
 */
@RestController
public class DefaultController {

    @GetMapping("/hello")
    @SecurityRequirement(name = "security")
    public String helloWorld() {
        return "world";
    }

}
