package com.jayxu.playground.spring.mvc;

import java.io.IOException;
import java.util.Map;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jayxu.wolfram.WolframService;

import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/wolfram")
public class WolframController {
    private WolframService service = WolframService.init();

    @GetMapping("/query/raw")
    @Cacheable("wolfram-raw")
    public Mono<String> queryRaw(@RequestParam String query)
            throws IOException {
        return Mono.just(
            this.service.query(query).execute().body().extractRawResult());
    }

    @GetMapping("/query/all")
    @Cacheable("wolfram-all")
    public Mono<Map<String, String>> queryAll(@RequestParam String query)
            throws IOException {
        return Mono.just(
            this.service.query(query).execute().body().extractAllResults());
    }
}