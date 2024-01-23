package com.task.controller.user;

import com.task.controller.user.dto.UserResponse;
import com.task.domain.User;

public class MapperUser {

    private MapperUser() {
        throw new IllegalStateException("Utility class");
    }


    public static UserResponse toUserResponse(User user) {
        return UserResponse.builder()
                .id(user.getId())
                .name(user.getName())
                .email(user.getUsername())
                .build();
    }


}
