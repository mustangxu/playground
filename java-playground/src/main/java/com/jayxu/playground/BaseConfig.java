/**
 * Authored by jayxu @2021
 */
package com.jayxu.playground;

import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * @author xujiajing
 */
@SpringBootConfiguration
@EnableWebMvc
@EnableJpaRepositories
@ComponentScan(basePackages = { "com.jayxu" })
public class BaseConfig {
}
