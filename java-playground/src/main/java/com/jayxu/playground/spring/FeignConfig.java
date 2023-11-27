/**
 * Authored by jayxu @2023
 */
package com.jayxu.playground.spring;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.openfeign.support.SpringMvcContract;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.jayxu.playground.mailgun.MailgunService;
import com.jayxu.playground.wolfram.WolframService;

import feign.Feign;
import feign.Logger.Level;
import feign.auth.BasicAuthRequestInterceptor;
import feign.form.spring.SpringFormEncoder;
import feign.jackson.JacksonDecoder;
import feign.slf4j.Slf4jLogger;

/**
 * @author jayxu
 */
@Configuration
public class FeignConfig {
    @Value("${mailgun.api-key}")
    private String apiKey;
    @Value("${mailgun.base-url}")
    private String baseUrl;

    @Bean
    MailgunService mailgunService() {
        return feignBuilder()
            .requestInterceptor(
                new BasicAuthRequestInterceptor("api", this.apiKey))
            .logger(new Slf4jLogger(MailgunService.class))
            .target(MailgunService.class, this.baseUrl);
    }

    @Bean
    WolframService wolframService() {
        return feignBuilder().logger(new Slf4jLogger(WolframService.class))
            .target(WolframService.class, WolframService.BASE_URL);
    }

    private static Feign.Builder feignBuilder() {
        return Feign.builder().logLevel(Level.HEADERS)
            .contract(new SpringMvcContract()).decoder(new JacksonDecoder())
            .encoder(new SpringFormEncoder());
    }
}
