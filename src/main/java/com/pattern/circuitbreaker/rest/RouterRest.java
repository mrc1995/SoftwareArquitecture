package com.pattern.circuitbreaker.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;
@Configuration
@RequiredArgsConstructor
public class RouterRest {

    @Bean
    public RouterFunction<ServerResponse> routerUser(UserHandler userRest) {
        return route(RequestPredicates.GET("/user"), userRest::user);
    }
}
