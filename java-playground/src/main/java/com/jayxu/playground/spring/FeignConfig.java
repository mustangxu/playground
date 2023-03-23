/**
 * Authored by jayxu @2023
 */
package com.jayxu.playground.spring;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.openfeign.support.SpringMvcContract;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.jayxu.playground.mailgun.MailgunService;

import feign.Feign;
import feign.Logger.Level;
import feign.auth.BasicAuthRequestInterceptor;
import feign.form.spring.SpringFormEncoder;
import feign.gson.GsonDecoder;
import feign.slf4j.Slf4jLogger;

/**
 * @author xujiajing
 */
@Configuration
public class FeignConfig {
    @Value("${mailgun.api-key}")
    private String apiKey;
    @Value("${mailgun.base-url}")
    private String baseUrl;

    @Bean
    MailgunService mailgunService() {
        return Feign.builder()
            .requestInterceptor(
                new BasicAuthRequestInterceptor("api", this.apiKey))
            .logger(new Slf4jLogger(MailgunService.class))
            .logLevel(Level.HEADERS).contract(new SpringMvcContract())
            .decoder(new GsonDecoder()).encoder(new SpringFormEncoder())
            .target(MailgunService.class, this.baseUrl);
    }
}
