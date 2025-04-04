///**
// * Authored by jayxu @2023
// */
//package com.jayxu.playground.spring;
//
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.client.RestClient;
//
///**
// * @author jayxu
// */
//@Configuration
//public class OpenAiConfig {
//    @Value("${openai.api-key}")
//    private String apikey;
//
////    @Bean
////    OpenAiService openAiService() {
////        return OpenAiService.init(this.apikey);
////    }
//
////    @Bean
////    StreamOpenAiService streamOpenAiService() {
////        return StreamOpenAiService.builder().debug(true).apikey(this.apikey)
////            .build().init();
////    }
//
//    @Bean
//    RestClient.Builder builder() {
//        return RestClient.builder();
//    }
//}
