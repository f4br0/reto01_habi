package com.task.controller.user;

import com.task.controller.user.dto.UserResponse;
import com.task.service.spec.IAuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {

    private final IAuthService authService;


    @GetMapping()
    public ResponseEntity<List<UserResponse>> getTask() {
        return ResponseEntity.ok(authService.findAll().map(MapperUser::toUserResponse).collectList().block());
    }


}
