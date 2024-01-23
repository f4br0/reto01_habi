package com.task.domain;

import com.task.domain.enums.Role;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class User extends UserAuth {
    private Integer id;
    private String username;
    private String name;
    private String password;
    private Role role;
}
