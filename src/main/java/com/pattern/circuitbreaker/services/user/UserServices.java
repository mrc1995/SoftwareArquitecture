package com.pattern.circuitbreaker.services.user;

import com.pattern.circuitbreaker.model.User;
import reactor.core.publisher.Mono;

public interface UserServices {
    Mono<User> user();
}
