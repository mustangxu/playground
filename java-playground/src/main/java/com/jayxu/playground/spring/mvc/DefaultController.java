/**
 * Authored by jayxu @2021
 */
package com.jayxu.playground.spring.mvc;

import java.time.Duration;
import java.util.Date;

import org.jasypt.encryption.StringEncryptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.core.env.Environment;
import org.springframework.http.MediaType;
import org.springframework.http.codec.ServerSentEvent;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import reactor.core.publisher.Flux;

/**
 * @author jayxu
 */
@RestController
public class DefaultController {
    @Autowired
    private StringEncryptor encryptor;
    @Autowired
    private Environment env;

    @GetMapping("/hello")
    @SecurityRequirement(name = "security")
    public String helloWorld() {
        return "world";
    }

    @GetMapping("/enc")
    public String encrypt(@RequestParam String raw) {
        return this.encryptor.encrypt(raw);
    }

    @GetMapping("/env")
    public String getEnv(@RequestParam String key) {
        return this.env.getProperty(key);
    }

    @GetMapping(path = "/sse/event",
            produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<ServerSentEvent<Date>> sseEvent() {
        return Flux.interval(Duration.ofSeconds(5))
            .map(sequence -> ServerSentEvent.<Date> builder()
                .id(String.valueOf(sequence)).event("periodic-event")
                .data(new Date()).build());
    }

    @GetMapping(path = "/sse/consume",
            produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<Long> consumeEvent() {
        var client = WebClient.create("http://localhost:8000");
        ParameterizedTypeReference<ServerSentEvent<Date>> type = new ParameterizedTypeReference<>() {
        };

        var flux = client.get().uri("/sse/event").retrieve().bodyToFlux(type);
        return flux.mapNotNull(ServerSentEvent::data).mapNotNull(Date::getTime);
    }
}
