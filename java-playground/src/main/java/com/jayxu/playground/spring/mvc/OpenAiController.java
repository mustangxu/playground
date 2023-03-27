/**
 * Authored by jayxu @2023
 */
package com.jayxu.playground.spring.mvc;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jayxu.openai4j.OpenAiService;
import com.jayxu.openai4j.StreamCallback;
import com.jayxu.openai4j.model.CompletionRequest;
import com.jayxu.openai4j.model.CompletionResponse;
import com.jayxu.openai4j.model.ImageRequest;
import com.jayxu.openai4j.model.Message;
import com.jayxu.openai4j.model.Model;
import com.jayxu.openai4j.model.ModelType;
import com.jayxu.openai4j.model.Url;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

/**
 * @author xujiajing
 */
@Slf4j
@RestController("/openai")
public class OpenAiController {
    private static final String EOF = "[DONE]";
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
    public Flux<String> createChat(@RequestBody String content,
            @RequestParam ModelType model, @RequestParam boolean stream) {
        var msg = Message.builder().content(content).build();
        var req = CompletionRequest.builder().model(model.value())
            .messages(Arrays.asList(msg)).stream(stream).build();

        if (stream) {
            var flux = Flux.<CompletionResponse> create(e -> {
                this.service.asyncCreateChat(req)
                    .enqueue(new StreamCallback<>(e, CompletionResponse.class));
            });

            return flux.map(r -> r.getChoices().get(0).getDelta())
                .filter(Objects::nonNull).map(Message::getContent);
        }

        return Flux.just(this.service.createChat(req).execute().body()
            .getChoices().get(0).getMessage().getContent());
    }

    @SneakyThrows
    @PostMapping("/completions")
    public Flux<String> createCompletions(@RequestBody List<String> prompt,
            @RequestParam(defaultValue = "text-davinci-003") String model,
            @RequestParam(required = false) Integer maxTokens,
            @RequestParam boolean stream) {
        var req = CompletionRequest.builder().model(model).prompt(prompt)
            .maxTokens(maxTokens).stream(stream).build();

        if (stream) {
            var flux = Flux.<CompletionResponse> create(e -> {
                this.service.asyncCreateCompletions(req)
                    .enqueue(new StreamCallback<>(e, CompletionResponse.class));
            });

            return flux.map(r -> r.getChoices().get(0).getText());
        }

        return Flux.just(this.service.createCompletions(req).execute().body()
            .getChoices().get(0).getText());
    }

    @SneakyThrows
    @PostMapping("/image/create")
    public List<Url> createImage(@RequestBody String prompt,
            @RequestParam(defaultValue = "1024x1024") String size) {
        var req = ImageRequest.builder().size(size).prompt(prompt).build();

        return this.service.createImage(req).execute().body().getData();
    }
}
