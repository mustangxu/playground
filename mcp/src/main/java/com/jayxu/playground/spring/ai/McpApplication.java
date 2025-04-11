/**
 * Authored by jayxu @2021
 */
package com.jayxu.playground.spring.ai;

import java.util.Map;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.reactive.function.client.WebClient;
import io.modelcontextprotocol.client.McpClient;
import io.modelcontextprotocol.client.transport.WebFluxSseClientTransport;
import io.modelcontextprotocol.spec.McpSchema;

import lombok.extern.slf4j.Slf4j;

/**
 * @author jayxu
 */
@Slf4j
@SpringBootApplication
@ComponentScan(basePackages = "com.jayxu")
public class McpApplication {
    @SuppressWarnings("resource")
    public static void main(String[] args) {
        SpringApplication.run(McpApplication.class, args);

        Thread.ofVirtual().start(() -> {
            try (var client = McpClient.sync(
                    new WebFluxSseClientTransport(WebClient.builder().baseUrl("http://localhost:8080"))).build()) {

                client.initialize();
                client.ping();

                // List and demonstrate tools
                client.listTools().tools().forEach(t -> log.info("Tools: {}", t));

                var weatherForcastResult = client.callTool(new McpSchema.CallToolRequest("getWeatherForecastByLocation",
                                                                                         Map.of("latitude", "47.6062",
                                                                                                "longitude",
                                                                                                "-122.3321")));
                log.info("Weather Forcast: {}", weatherForcastResult);

                var alertResult = client.callTool(new McpSchema.CallToolRequest("getAlerts", Map.of("state", "NY")));
                log.info("Alert Response: {}", alertResult);
            }
        });
    }
}
