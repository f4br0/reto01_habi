package com.task.service;

import com.task.controller.auth.dto.AuthResponse;
import com.task.dto.Role;
import com.task.dto.User;
import com.task.repository.spec.IUserDatabase;
import com.task.service.spec.IAuthService;
import com.task.service.spec.IJwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService implements IAuthService {

    private final IUserDatabase userDatabase;
    private final IJwtService jwtService;

    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;

    @Override
    public AuthResponse login(User request) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
        String token = jwtService.getToken(request.getUsername());
        return AuthResponse.builder()
                .token(token)
                .build();

    }

    @Override
    public AuthResponse register(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole(Role.USER);
        userDatabase.save(user);
        return AuthResponse.builder()
                .token(jwtService.getToken(user.getUsername()))
                .build();

    }

}
