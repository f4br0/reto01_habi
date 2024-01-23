package com.task.repository.spec;

import com.task.domain.User;
import reactor.core.publisher.Flux;

public interface IUserDatabase {
    User save(User userData);
    User findByUsername(String username);
    Flux<User> findAll();
}
