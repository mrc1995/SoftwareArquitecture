package com.pattern.circuitbreaker.services.user.impl;

import com.pattern.circuitbreaker.model.User;
import com.pattern.circuitbreaker.restconsumer.UserRestConsumer;
import com.pattern.circuitbreaker.services.user.UserServices;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class UserServicesImpl implements UserServices {

    private final UserRestConsumer userRestConsumer;

    public Mono<User> user(){
        return userRestConsumer.user();
    }
}
