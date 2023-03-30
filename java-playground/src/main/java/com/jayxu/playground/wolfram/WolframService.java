/**
 * Authored by jayxu @2023
 */
package com.jayxu.playground.wolfram;

import org.springframework.cloud.openfeign.support.SpringMvcContract;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import feign.Feign;
import feign.jackson.JacksonDecoder;

/**
 * @author xujiajing
 */
public interface WolframService {
    String BASE_URL = "https://api.wolframalpha.com/v2/";
    String APP_ID = "THX44E-3GUJQYYWQL";

    static WolframService init() {
        return Feign.builder().contract(new SpringMvcContract())
            .decoder(new JacksonDecoder())
            .target(WolframService.class, BASE_URL);
    }

    @GetMapping("/query")
    WolframResponse query(@RequestParam("appid") String appID,
            @RequestParam("input") String input,
            @RequestParam("output") String output);

    default WolframResponse query(String input) {
        return this.query(APP_ID, input, "json");
    }
}
