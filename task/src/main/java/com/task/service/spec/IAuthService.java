package com.task.service.spec;

import com.task.controller.auth.dto.AuthResponse;
import com.task.dto.User;

public interface IAuthService {
    AuthResponse login(User request);

    AuthResponse register(User request);
}
