/**
 * Authored by jayxu @2021
 */
package com.jayxu.playground.spring.mvc;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author xujiajing
 */
@RestController
public class DefaultController {

    @RequestMapping("/hello")
    public String helloWorld() {
        return "world";
    }

}
