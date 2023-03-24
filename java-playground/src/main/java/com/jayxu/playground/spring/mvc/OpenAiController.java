/**
 * Authored by jayxu @2023
 */
package com.jayxu.playground.spring.mvc;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jayxu.openai4j.OpenAiService;
import com.jayxu.openai4j.model.ChatRequest;
import com.jayxu.openai4j.model.Message;
import com.jayxu.openai4j.model.Model;
import com.jayxu.openai4j.model.ModelType;

import lombok.SneakyThrows;

/**
 * @author xujiajing
 */
@RestController("/openai")
public class OpenAiController {
    @Autowired
    private OpenAiService service;

    @GetMapping("/models")
    @SneakyThrows
    public List<String> getModels() {
        return this.service.listModels().execute().body().getData().stream()
            .map(Model::getId).sorted().toList();
    }

    @SneakyThrows
    @PostMapping("/chat")
    String chat(@RequestBody String content, @RequestParam ModelType model) {
        var msg = Message.builder().content(content).build();
        var req = ChatRequest.builder().model(model.value())
            .messages(Arrays.asList(msg)).build();

        return this.service.chat(req).execute().body().getChoices().get(0)
            .getMessage().getContent();
    }
}
