/**
 * Authored by jayxu @2021
 */
package com.jayxu.playground.spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author jayxu
 */
@SpringBootApplication
@ComponentScan(basePackages = "com.jayxu")
public class Http3Application {
    @SuppressWarnings("resource")
    public static void main(String[] args) {
        SpringApplication.run(Http3Application.class, args);
    }
}
