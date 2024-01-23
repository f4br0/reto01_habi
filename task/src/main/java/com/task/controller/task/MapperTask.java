package com.task.controller.task;

import com.task.controller.task.dto.GetTaskResponse;
import com.task.controller.task.dto.NewTaskRequest;
import com.task.controller.task.dto.NewTaskResponse;
import com.task.controller.task.dto.UpdateTaskRequest;
import com.task.controller.task.dto.UpdateTaskResponse;
import com.task.domain.Task;
import org.springframework.data.domain.Page;

import java.util.List;

public class MapperTask {

    private MapperTask() {
        throw new IllegalStateException("Utility class");
    }


    public static Task toTask(NewTaskRequest request, Integer fromUser) {
        return  Task.builder()
                .title(request.getTitle())
                .toUser(request.getToUser())
                .fromUser(fromUser)
                .build();
    }


    public static NewTaskResponse toNewTaskResponse(Task task) {
        return NewTaskResponse.builder()
                .id(task.getId())
                .build();
    }

    public static Task toTask(UpdateTaskRequest request) {
        return  Task.builder()
                .build();
    }

    public static UpdateTaskResponse toUpdateTaskResponse(Task update) {
        return UpdateTaskResponse.builder().build();
    }


    public static GetTaskResponse toGetTaskResponse(Page<Task> task) {
        return GetTaskResponse.builder()
                .tasks(task.getContent())
                .totalItems(task.getTotalElements())
                .build();
    }
}
