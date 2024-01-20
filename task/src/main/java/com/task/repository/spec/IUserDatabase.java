package com.task.repository.spec;

import com.task.dto.User;

public interface IUserDatabase {
    User save(User userData);

    User findByUsername(String username);
}
