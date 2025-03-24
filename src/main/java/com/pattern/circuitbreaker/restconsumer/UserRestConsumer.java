package com.pattern.circuitbreaker.restconsumer;

import com.pattern.circuitbreaker.model.User;
import reactor.core.publisher.Mono;

public interface UserRestConsumer {

    Mono<User> user();
}
