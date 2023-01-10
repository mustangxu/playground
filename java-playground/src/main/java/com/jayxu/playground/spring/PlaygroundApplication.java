/**
 * Authored by jayxu @2021
 */
package com.jayxu.playground.spring;

import org.apache.commons.lang3.ArrayUtils;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

/**
 * @author xujiajing
 */
@SpringBootApplication
@EnableCaching
// @EnableWebFlux
// @EnableJpaRepositories
// @ComponentScan(basePackages = { "com.jayxu" })
public class PlaygroundApplication {
    private static final String[] skipArgs = {
        "--spring.output.ansi.enabled=always" };

    @SuppressWarnings("resource")
    public static void main(String[] args) {
        SpringApplication.run(PlaygroundApplication.class,
            ArrayUtils.removeElements(args, skipArgs));
    }
}
