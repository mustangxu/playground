/**
 * Authored by jayxu @2023
 */
package com.jayxu.playground.spring.mvc;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jayxu.openai4j.OpenAiService;
import com.jayxu.openai4j.StreamOpenAiService;
import com.jayxu.openai4j.model.CompletionRequest;
import com.jayxu.openai4j.model.CompletionResponse;
import com.jayxu.openai4j.model.ImageRequest;
import com.jayxu.openai4j.model.ImageResponse;
import com.jayxu.openai4j.model.Message;
import com.jayxu.openai4j.model.Model;
import com.jayxu.openai4j.model.ModelList;
import com.jayxu.openai4j.model.ModelType;
import com.jayxu.openai4j.model.Url;

import lombok.SneakyThrows;
import reactor.core.publisher.Flux;

/**
 * @author jayxu
 */
@RestController
@RequestMapping("/openai")
public class OpenAiController {
    @Autowired
    private OpenAiService service;
    @Autowired
    private StreamOpenAiService streamService;

    @GetMapping("/models")
    @SneakyThrows
    public List<String> getModels() {
        ModelList body = this.service.listModels().execute().body();
        return body == null ? Collections.EMPTY_LIST
            : body.getData().stream().map(Model::getId).sorted().toList();
    }

    @SneakyThrows
    @PostMapping(path = "/chat", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<String> createChat(@RequestBody String content,
            @RequestParam ModelType model, @RequestParam boolean stream) {
        var msg = Message.builder().content(content).build();
        var req = CompletionRequest.builder().model(model.value()).message(msg)
            .stream(stream).build();

        if (stream) {
            return this.streamService.createChat(req)
                .mapNotNull(r -> r.getChoices().get(0).getDelta())
                .mapNotNull(Message::getContent);
        }

        CompletionResponse body = this.service.createChat(req).execute().body();
        return body == null ? Flux.empty()
            : Flux.just(body.getChoices().get(0).getMessage().getContent());
    }

    @SneakyThrows
    @PostMapping(path = "/completions",
            produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<String> createCompletions(@RequestBody List<String> prompt,
            @RequestParam ModelType model, @RequestParam boolean stream) {
        var req = CompletionRequest.builder().model(model.value())
            .prompt(prompt).stream(stream).build();

        if (stream) {
            return this.streamService.createCompletions(req)
                .mapNotNull(r -> r.getChoices().get(0).getText());
        }

        CompletionResponse body = this.service.createCompletions(req).execute()
            .body();
        return body == null ? Flux.empty()
            : Flux.just(body.getChoices().get(0).getText());
    }

    @SneakyThrows
    @PostMapping("/image/create")
    public List<Url> createImage(@RequestBody String prompt,
            @RequestParam(defaultValue = "1024x1024") String size) {
        var req = ImageRequest.builder().size(size).prompt(prompt).build();

        ImageResponse body = this.service.createImage(req).execute().body();
        return body == null ? Collections.EMPTY_LIST : body.getData();
    }
}
