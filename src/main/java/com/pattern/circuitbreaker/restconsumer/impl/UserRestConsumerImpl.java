package com.pattern.circuitbreaker.restconsumer.impl;

import com.pattern.circuitbreaker.model.Error;
import com.pattern.circuitbreaker.model.TechnicalException;
import com.pattern.circuitbreaker.model.User;
import com.pattern.circuitbreaker.restconsumer.UserRestConsumer;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.client.circuitbreaker.ReactiveCircuitBreaker;
import org.springframework.cloud.client.circuitbreaker.ReactiveCircuitBreakerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.sql.Timestamp;
import java.time.LocalDateTime;


@Repository
public class UserRestConsumerImpl implements UserRestConsumer {

    private static final Logger LOG = LoggerFactory.getLogger(UserRestConsumerImpl.class);
    private final WebClient webClient;
    private final ReactiveCircuitBreaker reactiveCircuitBreaker;

    public UserRestConsumerImpl(ReactiveCircuitBreakerFactory reactiveCircuitBreakerFactory){
        this.webClient = WebClient.builder().baseUrl("http://localhost:3000").build();
        this.reactiveCircuitBreaker = reactiveCircuitBreakerFactory.create("user");
    }

    @CircuitBreaker(name = "user")
    public Mono<User> user(){
        return reactiveCircuitBreaker.run(webClient
            .get()
            .uri("/user-data")
            .retrieve()
            .bodyToMono(User.class),
            throwable -> {
                LOG.warn("Error making request to user service", throwable);
                return Mono.error(new TechnicalException(throwable));
            });
    }
}
