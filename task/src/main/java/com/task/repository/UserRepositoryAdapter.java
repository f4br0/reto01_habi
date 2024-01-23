package com.task.repository;

import com.task.domain.User;
import com.task.repository.entity.UserData;
import com.task.repository.spec.IUserDatabase;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

import java.util.Optional;

@Component
@AllArgsConstructor
public class UserRepositoryAdapter implements IUserDatabase {

    private final UserRepository userRepository;

    @Override
    public User save(User user) {
        UserData userData = userRepository.save(toUserData(user));
        return toUser(userData);
    }

    @Override
    public User findByUsername(String username) {
        return Optional.of(userRepository.findByUsername(username)).map(this::toUser).get();
    }

    @Override
    public Flux<User> findAll() {
        return Flux.fromIterable(userRepository.findAll()).map(this::toUser);
    }

    private UserData toUserData(User user) {
        return UserData.builder()
                .role(user.getRole())
                .password(user.getPassword())
                .name(user.getName())
                .username(user.getUsername())
                .build();
    }

    private User toUser(UserData userData) {
        return User.builder()
                .id(userData.getId())
                .name(userData.getName())
                .password(userData.getPassword())
                .role(userData.getRole())
                .username(userData.getUsername())
                .build();
    }
}
