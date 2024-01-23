package com.task.repository;

import com.task.domain.Task;
import com.task.repository.entity.TaskData;
import com.task.repository.entity.UserData;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<TaskData, Integer> {
    Page<Task> findAllByFromUserOrToUser(Integer userFrom, Integer userTo, Pageable pageable);
}
