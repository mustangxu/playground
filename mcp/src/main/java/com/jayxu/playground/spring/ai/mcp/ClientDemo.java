/*
 * Authored by jayxu @2025
 */

package com.jayxu.playground.spring.ai.mcp;

import java.util.Map;

import org.springframework.ai.mcp.client.webflux.transport.WebClientStreamableHttpTransport;
import org.springframework.web.reactive.function.client.WebClient;

import io.modelcontextprotocol.client.McpClient;
import io.modelcontextprotocol.spec.McpSchema;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ClientDemo {
    public static void main(String[] args) {
        new ClientDemo().start();
    }

    public void start() {
        try (var client = McpClient.sync(
                        WebClientStreamableHttpTransport.builder(WebClient.builder().baseUrl("http://127.0.0.1:8080")).build())
                .build()) {
            client.initialize();
            client.ping();

            client.listTools().tools().forEach(t -> log.info("{}", t));

            log.info("Weather Forcast: {}", client.callTool(
                    McpSchema.CallToolRequest.builder("getWeatherForecastByLocation")
                            .arguments(Map.of("latitude", "47.6062", "longitude", "-122.3321")).build()));

            log.info("Alerts: {}", client.callTool(
                    McpSchema.CallToolRequest.builder("getAlerts").arguments(Map.of("state", "NY")).build()));
        }
    }
}
