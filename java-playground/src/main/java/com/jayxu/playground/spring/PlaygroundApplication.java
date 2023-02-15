/**
 * Authored by jayxu @2021
 */
package com.jayxu.playground.spring;

import org.apache.commons.lang3.ArrayUtils;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityScheme;

/**
 * @author xujiajing
 */
@SpringBootApplication
// @EnableCaching
// @EnableWebSocket
// @EnableWebFlux
// @EnableJpaRepositories
@ComponentScan(basePackages = { "com.jayxu" })
@SecurityScheme(name = "security", type = SecuritySchemeType.APIKEY,
        in = SecuritySchemeIn.HEADER)
public class PlaygroundApplication {
    private static final String[] skipArgs = {
        "--spring.output.ansi.enabled=always" };

    @SuppressWarnings("resource")
    public static void main(String[] args) {
        SpringApplication.run(PlaygroundApplication.class,
            ArrayUtils.removeElements(args, skipArgs));
    }
}
