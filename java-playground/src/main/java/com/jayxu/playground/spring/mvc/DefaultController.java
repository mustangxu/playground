/**
 * Authored by jayxu @2021
 */
package com.jayxu.playground.spring.mvc;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import reactor.core.publisher.Mono;

/**
 * @author xujiajing
 */
@RestController
public class DefaultController {

    @GetMapping("/hello")
    public Mono<String> helloWorld() {
        return Mono.just("world");
    }

}
