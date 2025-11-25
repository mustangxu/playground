/*
 * Authored by jayxu @2025
 */

package com.jayxu.playground.spring.ai.mcp;

import java.util.Map;

import org.springframework.web.reactive.function.client.WebClient;

import com.fasterxml.jackson.databind.ObjectMapper;

import io.modelcontextprotocol.client.McpClient;
import io.modelcontextprotocol.client.transport.WebFluxSseClientTransport;
import io.modelcontextprotocol.json.jackson.JacksonMcpJsonMapper;
import io.modelcontextprotocol.spec.McpSchema;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ClientDemo {
    public static void main(String[] args) {
        new ClientDemo().start();
    }

    public void start() {
        try (var client = McpClient.sync(
                new WebFluxSseClientTransport(WebClient.builder().baseUrl("http://127.0.0.1:8080"),
                        new JacksonMcpJsonMapper(new ObjectMapper()))).build()) {

            client.initialize();
            client.ping();

            client.listTools().tools().forEach(t -> log.info("{}", t));

            log.info("Weather Forcast: {}", client.callTool(
                    new McpSchema.CallToolRequest("getWeatherForecastByLocation",
                            Map.of("latitude", "47.6062", "longitude", "-122.3321"))));

            log.info("Alerts: {}", client.callTool(new McpSchema.CallToolRequest("getAlerts", Map.of("state", "NY"))));
        }
    }
}
