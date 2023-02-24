package com.jayxu.playground.spring.mvc;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jayxu.playground.wolfram.WolframService;

@RestController
@RequestMapping("/wolfram")
public class WolframController {
    @Autowired
    private WolframService service;

    @GetMapping("/query/raw")
    @Cacheable("wolfram-raw")
    public String queryRaw(@RequestParam String query) {
        return this.service.query(query).extractRawResult();
    }

    @GetMapping("/query/all")
    @Cacheable("wolfram-all")
    public Map<String, String> queryAll(@RequestParam String query) {
        return this.service.query(query).extractAllResults();
    }
}
