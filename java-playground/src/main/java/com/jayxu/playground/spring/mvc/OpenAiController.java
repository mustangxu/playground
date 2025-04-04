///**
// * Authored by jayxu @2023
// */
//package com.jayxu.playground.spring.mvc;
//
//import java.util.Collections;
//import java.util.List;
//
//import org.springframework.ai.chat.prompt.Prompt;
//import org.springframework.ai.image.ImagePrompt;
//import org.springframework.ai.image.ImageResponse;
//import org.springframework.ai.openai.OpenAiChatModel;
//import org.springframework.ai.openai.OpenAiChatOptions;
//import org.springframework.ai.openai.OpenAiImageModel;
//import org.springframework.ai.openai.OpenAiImageOptions;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.MediaType;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RestController;
//
//import com.jayxu.openai4j.OpenAiService;
//import com.jayxu.openai4j.model.Model;
//import com.jayxu.openai4j.model.ModelList;
//
//import lombok.SneakyThrows;
//import reactor.core.publisher.Flux;
//
///**
// * @author jayxu
// */
//@RestController
//@RequestMapping("/openai")
//@Deprecated
//public class OpenAiController {
//    @Autowired
//    private OpenAiService service;
//    @Autowired
//    private OpenAiChatModel client;
//    @Autowired
//    private OpenAiImageModel imageClient;
//
//    @GetMapping("/models")
//    @SneakyThrows
//    public List<String> getModels() {
//        ModelList body = this.service.listModels().execute().body();
//        return body == null ? Collections.EMPTY_LIST
//            : body.getData().stream().map(Model::getId).sorted().toList();
//    }
//
//    @SneakyThrows
//    @PostMapping(path = "/chat", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
//    public Flux<String> createChat(@RequestBody String prompt,
//            @RequestParam ModelType model,
//            @RequestParam(defaultValue = "false") boolean stream) {
//        var p = new Prompt(prompt,
//            OpenAiChatOptions.builder().model(model.value()).build());
//
//        if (stream) {
//            return this.client.stream(p)
//                .mapNotNull(r -> r.getResult().getOutput().getText());
//        }
//
//        return Flux.just(this.client.call(p).getResult().getOutput().getText());
//    }
//
////    @SneakyThrows
////    @PostMapping(path = "/completions",
////            produces = MediaType.TEXT_EVENT_STREAM_VALUE)
////    public Flux<String> createCompletions(@RequestBody List<String> prompt,
////            @RequestParam ModelType model,
////            @RequestParam(defaultValue = "false") boolean stream) {
////        var req = CompletionRequest.builder().model(model.value())
////            .prompt(prompt).stream(stream).build();
////
////        if (stream) {
////            return this.streamService.createCompletions(req)
////                .mapNotNull(r -> r.getChoices().get(0).getText());
////        }
////
////        CompletionResponse body = this.service.createCompletions(req).execute()
////            .body();
////        return body == null ? Flux.empty()
////            : Flux.just(body.getChoices().get(0).getText());
////    }
//
//    @SneakyThrows
//    @PostMapping("/image/create")
//    public Flux<String> createImage(@RequestBody String prompt,
//            @RequestParam ModelType model,
//            @RequestParam(defaultValue = "1024") int width,
//            @RequestParam(defaultValue = "1024") int height,
//            @RequestParam(defaultValue = "natural") String style,
//            @RequestParam(defaultValue = "1") int n) {
//        var p = new ImagePrompt(prompt,
//            OpenAiImageOptions.builder().withModel(model.value())
//                .withHeight(height).withWidth(width).withQuality("hd")
//                .withStyle(style).withN(n).build());
//        ImageResponse body = this.imageClient.call(p);
//        return body == null ? Flux.empty()
//            : Flux.fromStream(body.getResults().stream())
//                .mapNotNull(r -> r.getOutput().getUrl());
//    }
//
//    public enum ModelType {
//        GPT_35_TURBO("gpt-3.5-turbo"),
//        TEXT_DAVINCI_003("text-davinci-003"),
//        DALL_E_2("dall-e-2"),
//        DALL_E_3("dall-e-3");
//
//        private final String value;
//
//        ModelType(String value) {
//            this.value = value;
//        }
//
//        public String value() {
//            return this.value;
//        }
//    }
//}
