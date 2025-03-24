package com.pattern.circuitbreaker.restconsumer.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.web.reactive.function.client.WebClient;

public class RestConsumerConfig {

    @Bean
    public WebClient webClient(WebClient.Builder webClientBuilder){
        return webClientBuilder
                .baseUrl("http://localhost:3000")
                .defaultHeader(HttpHeaders.CONTENT_TYPE, "application/json")
                .build();
    }
}
