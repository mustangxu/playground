/**
 * Authored by jayxu @2021
 */
package com.jayxu.playground.spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityScheme;

import lombok.extern.slf4j.Slf4j;

/**
 * @author jayxu
 */
@Slf4j
@SpringBootApplication
// @EnableCaching
// @EnableWebSocket
// @EnableWebFlux
// @EnableJpaRepositories
@ComponentScan(basePackages = "com.jayxu")
@EnableFeignClients(basePackages = "com.jayxu")
@SecurityScheme(name = "security", type = SecuritySchemeType.APIKEY, in = SecuritySchemeIn.HEADER)
//@EnableDiscoveryClient
public class PlaygroundApplication {
    //    private static final String[] skipArgs = {
    //        "--spring.output.ansi.enabled=always" };

    @SuppressWarnings("resource")
    static void main(String[] args) {
        SpringApplication.run(PlaygroundApplication.class, args);
    }
}
