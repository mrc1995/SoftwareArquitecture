package com.pattern.circuitbreaker.rest;

import com.pattern.circuitbreaker.model.Error;
import org.springframework.boot.autoconfigure.web.WebProperties;
import org.springframework.boot.autoconfigure.web.reactive.error.AbstractErrorWebExceptionHandler;
import org.springframework.boot.web.reactive.error.ErrorAttributes;
import org.springframework.context.ApplicationContext;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.codec.ServerCodecConfigurer;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.*;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

@Component
@Order(-2)
public class ExceptionHandler extends AbstractErrorWebExceptionHandler {


    /**
     * Create a new {@code AbstractErrorWebExceptionHandler}.
     *
     * @param errorAttributes    the error attributes
     * @param applicationContext the application context
     * @since 2.4.0
     */
    public ExceptionHandler(ErrorAttributes errorAttributes, ApplicationContext applicationContext,
                            ServerCodecConfigurer serverCodecConfigurer) {
        super(errorAttributes, new WebProperties.Resources(), applicationContext);
        this.setMessageWriters(serverCodecConfigurer.getWriters());
    }

    @Override
    protected RouterFunction<ServerResponse> getRoutingFunction(ErrorAttributes errorAttributes) {
        return RouterFunctions.route(RequestPredicates.all(), this::buildError);
    }

    private Mono<ServerResponse> buildError(ServerRequest serverRequest) {
        Throwable e = getError(serverRequest);
        return ServerResponse
                .status(HttpStatus.SERVICE_UNAVAILABLE)
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(Error.builder()
                        .method(serverRequest.method().name())
                        .path(serverRequest.path())
                        .reason(e.getMessage())
                        .timestamp(LocalDateTime.now())
                        .build());
    }
}
