package com.task.service.spec;

import com.task.domain.Task;
import org.springframework.data.domain.Page;

public interface ITaskService {
    Task create(Task task);
    Task update(Task task);
    Page<Task> getTask(String username, Integer pageNumber, Integer pageSize);
}
