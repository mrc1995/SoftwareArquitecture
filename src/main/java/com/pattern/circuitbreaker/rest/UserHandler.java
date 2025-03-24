package com.pattern.circuitbreaker.rest;

import com.pattern.circuitbreaker.model.Error;
import com.pattern.circuitbreaker.model.TechnicalException;
import com.pattern.circuitbreaker.model.User;
import com.pattern.circuitbreaker.services.user.UserServices;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.util.Objects;

@Component
@RequiredArgsConstructor
public class UserHandler {

    private final UserServices services;
    public Mono<ServerResponse> user(ServerRequest serverRequest){
        return ServerResponse
                .ok()
                .body(services.user(), User.class);
    }
}
