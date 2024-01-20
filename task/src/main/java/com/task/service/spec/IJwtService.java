package com.task.service.spec;

import org.springframework.security.core.userdetails.UserDetails;

public interface IJwtService {

    String getToken(String username);

    String getUsernameFromToken(String token);

    boolean isTokenValid(String token, UserDetails userDetails);
}
