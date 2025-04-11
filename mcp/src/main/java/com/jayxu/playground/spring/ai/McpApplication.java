/**
 * Authored by jayxu @2021
 */
package com.jayxu.playground.spring.ai;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import com.jayxu.playground.spring.ai.mcp.ClientDemo;

/**
 * @author jayxu
 */
@SpringBootApplication
@ComponentScan(basePackages = "com.jayxu")
public class McpApplication {
    @SuppressWarnings("resource")
    public static void main(String[] args) {
        SpringApplication.run(McpApplication.class, args);

        Thread.ofVirtual().start(() -> new ClientDemo().start());
    }
}
