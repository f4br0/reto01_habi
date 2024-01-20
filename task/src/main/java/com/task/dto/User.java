package com.task.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class User extends UserAuth {
    private String username;
    private String name;
    private String password;
    private Role role;
}
