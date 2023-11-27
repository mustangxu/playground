/**
 * Authored by jayxu @2023
 */
package com.jayxu.playground.spring;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.jayxu.openai4j.OpenAiService;
import com.jayxu.openai4j.StreamOpenAiService;

/**
 * @author jayxu
 */
@Configuration
public class OpenAiConfig {
    @Value("${openai.api-key}")
    private String apikey;

    @Bean
    OpenAiService openAiService() {
        return OpenAiService.init(this.apikey);
    }

    @Bean
    StreamOpenAiService streamOpenAiService() {
        return StreamOpenAiService.builder().debug(true).apikey(this.apikey)
            .build().init();
    }
}
