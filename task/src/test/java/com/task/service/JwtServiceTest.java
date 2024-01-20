package com.task.service;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JwtServiceTest {

    @Test
    public void testGetUsernameFromToken() {
        JwtService jwtService = new JwtService();
        String token = jwtService.getToken("testuser");
        assertEquals("testuser", jwtService.getUsernameFromToken(token));
    }

}
