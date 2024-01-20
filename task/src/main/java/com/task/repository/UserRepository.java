package com.task.repository;

import com.task.repository.entity.UserData;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserData, Integer> {
    UserData findByUsername(String username);
}
