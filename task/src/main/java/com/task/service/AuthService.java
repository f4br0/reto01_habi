package com.task.service;

import com.task.controller.auth.dto.AuthResponse;
import com.task.domain.enums.Role;
import com.task.domain.User;
import com.task.domain.exception.BusinessException;
import com.task.repository.spec.IUserDatabase;
import com.task.service.spec.IAuthService;
import com.task.service.spec.IJwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@Service
@RequiredArgsConstructor
public class AuthService implements IAuthService {

    private final IUserDatabase userDatabase;
    private final IJwtService jwtService;

    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;

    @Override
    public AuthResponse login(User request) {
        Authentication auth;
                try {
                    auth =   authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
                } catch (BadCredentialsException ex){
                    throw new BusinessException(401, "unauthorized");
                }
        String name = ((User) auth.getPrincipal()).getName();
        String token = jwtService.getToken(request.getUsername());
        return AuthResponse.builder()
                .token(token)
                .name(name)
                .email(request.getUsername())
                .build();

    }

    @Override
    public AuthResponse register(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole(Role.USER);
        userDatabase.save(user);
        return AuthResponse.builder()
                .token(jwtService.getToken(user.getUsername()))
                .name(user.getName())
                .email(user.getUsername())
                .build();

    }

    @Override
    public Flux<User> findAll() {
       return userDatabase.findAll();
    }

}
