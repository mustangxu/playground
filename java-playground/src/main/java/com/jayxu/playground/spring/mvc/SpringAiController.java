/**
 * Authored by jayxu-@2024
 */
package com.jayxu.playground.spring.mvc;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import reactor.core.publisher.Flux;

/**
 *
 */
@RestController
@RequestMapping("/ai")
public class SpringAiController {
    private ChatClient chatClient;

    public SpringAiController(ChatClient.Builder chatClientBuilder) {
        this.chatClient = chatClientBuilder.build();
    }

    @GetMapping("/ai")
    Flux<String> generation(String userInput) {
        return this.chatClient.prompt().user(userInput).stream().content();
    }
}
