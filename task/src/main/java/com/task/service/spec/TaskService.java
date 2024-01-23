package com.task.service.spec;

import com.task.domain.Task;
import com.task.repository.spec.ITaskDatabase;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class TaskService implements ITaskService{

    private final ITaskDatabase taskDatabase;

    @Override
    public Task create(Task task) {
        return taskDatabase.create(task);
    }

    @Override
    public Task update(Task task) {
        return taskDatabase.update(task);
    }

    @Override
    public Page<Task> getTask(String username, Integer pageNumber, Integer pageSize) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        return taskDatabase.findAll(username,pageable);
    }

}
