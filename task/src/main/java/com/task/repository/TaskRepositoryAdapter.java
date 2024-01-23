package com.task.repository;

import com.task.domain.Task;
import com.task.domain.User;
import com.task.repository.entity.TaskData;
import com.task.repository.entity.UserData;
import com.task.repository.spec.ITaskDatabase;
import com.task.repository.spec.IUserDatabase;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@AllArgsConstructor
public class TaskRepositoryAdapter implements ITaskDatabase {

    private final TaskRepository taskRepository;
    private final UserRepository userRepository;


    @Override
    public Task create(Task task) {
        return toDomain(taskRepository.save(toEntityData(task)));
    }

    @Override
    public Task update(Task task) {
        TaskData taskU = taskRepository.findById(task.getId()).get();
        taskU.setState(task.isState());
        taskU.setToUser(task.getToUser());
        return toDomain(taskRepository.save(taskU));
    }

    @Override
    public Page<Task> findAll(String username,Pageable pageable) {
        Integer user = userRepository.findByUsername(username).getId();
        return taskRepository.findAllByFromUserOrToUser(user,user,pageable);
    }

    private TaskData toEntityData(Task domain) {
        return TaskData.builder()
//                .toUser(userRepository.findById(domain.getToUser()).get())
                .toUser(domain.getToUser())
                .title(domain.getTitle())
//                .fromUser(userRepository.findById(domain.getFromUser()).get())
                .fromUser(domain.getFromUser())
                .build();
    }

    private Task toDomain(TaskData entityData) {
        return Task.builder()
                .id(entityData.getId())
                .build();
    }
}
