/**
 * Authored by jayxu-@2024
 */
package com.jayxu.playground.spring.mvc;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import io.milvus.client.MilvusServiceClient;
import reactor.core.publisher.Flux;

/**
 *
 */
@RestController
@RequestMapping("/ai")
public class SpringAiController {
    private final ChatClient chatClient;
    @Autowired
    private VectorStore vectorStore;

    public SpringAiController(ChatClient.Builder chatClientBuilder) {
        this.chatClient = chatClientBuilder.build();
    }

    @GetMapping("/chat")
    Flux<String> generation(String userInput) {
        return this.chatClient.prompt().user(userInput).stream().content();
    }

    @GetMapping("/vector/status")
    public String status() {
        var client = (MilvusServiceClient) this.vectorStore.getNativeClient().get();
        return client.checkHealth().toString();
    }
}
