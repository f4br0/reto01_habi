package com.task.controller.auth;

import com.task.controller.auth.dto.LoginRequest;
import com.task.controller.auth.dto.RegisterRequest;
import com.task.dto.User;

public class MapperAuth {
    private MapperAuth() {
        throw new IllegalStateException("Utility class");
    }

    public static User toUser(RegisterRequest request) {
        return User.builder()
                .username(request.getEmail())
                .name(request.getName())
                .password(request.getPassword())
                .build();
    }

    public static User toUser(LoginRequest request) {
        return User.builder()
                .username(request.getEmail())
                .password(request.getPassword())
                .build();
    }
}
