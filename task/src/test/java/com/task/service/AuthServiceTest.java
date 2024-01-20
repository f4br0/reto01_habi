package com.task.service;

import com.task.controller.auth.dto.AuthResponse;
import com.task.dto.Role;
import com.task.dto.User;
import com.task.repository.spec.IUserDatabase;
import com.task.service.spec.IJwtService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class AuthServiceTest {

    @Mock
    private IUserDatabase userDatabase;

    @Mock
    private IJwtService jwtService;

    @Mock
    private AuthenticationManager authenticationManager;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private AuthService authService;

    @BeforeEach
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testLogin() {
        User user = User.builder()
                .username("testuser")
                .password("testpassword")
                .build();

        when(jwtService.getToken(user.getUsername())).thenReturn("testtoken");

        AuthResponse response = authService.login(user);

        assertEquals("testtoken", response.getToken());
        verify(authenticationManager, times(1)).authenticate(any(UsernamePasswordAuthenticationToken.class));
    }

    @Test
    public void testRegister() {
        User user = User.builder()
                .username("testuser")
                .password("testpassword")
                .build();


        when(passwordEncoder.encode(user.getPassword())).thenReturn("testhash");

        AuthResponse response = authService.register(user);

        assertEquals("testhash", user.getPassword());
        assertEquals(Role.USER, user.getRole());
        verify(userDatabase, times(1)).save(user);
    }
}
