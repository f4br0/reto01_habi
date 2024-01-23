package com.task.service.spec;

import com.task.controller.auth.dto.AuthResponse;
import com.task.domain.User;
import reactor.core.publisher.Flux;

public interface IAuthService {
    AuthResponse login(User request);

    AuthResponse register(User request);

    Flux<User> findAll();
}
