/**
 * Authored by jayxu @2021
 */
package com.jayxu.playground.spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * @author xujiajing
 */
@SpringBootApplication
@EnableWebMvc
@EnableJpaRepositories
@ComponentScan(basePackages = { "com.jayxu" })
public class PlaygroundApplication {
    public static void main(String[] args) {
        SpringApplication.run(PlaygroundApplication.class, args);
    }
}