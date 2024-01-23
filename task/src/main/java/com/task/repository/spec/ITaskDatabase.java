package com.task.repository.spec;

import com.task.domain.Task;
import com.task.repository.entity.UserData;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ITaskDatabase {
    Task create(Task task);
    Task update(Task task);
    Page<Task> findAll(String username,Pageable pageable);
}
