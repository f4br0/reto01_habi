package com.task.controller.task;

import com.task.controller.task.dto.GetTaskResponse;
import com.task.controller.task.dto.NewTaskRequest;
import com.task.controller.task.dto.NewTaskResponse;
import com.task.controller.task.dto.UpdateTaskRequest;
import com.task.controller.task.dto.UpdateTaskResponse;
import com.task.domain.User;
import com.task.service.spec.ITaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/task")
@RequiredArgsConstructor
public class TaskController {

    private final ITaskService taskService;

    private User getUser() {
        return ((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
    }

    @PostMapping()
    public ResponseEntity<NewTaskResponse> createTask(@RequestBody NewTaskRequest request) {
        return ResponseEntity.ok(MapperTask.toNewTaskResponse(taskService.create(MapperTask.toTask(request, getUser().getId()))));
    }

    @GetMapping("{pageNumber}/{pageSize}")
    public ResponseEntity<GetTaskResponse> getTask(@PathVariable Integer pageNumber, @PathVariable Integer pageSize) {
        return ResponseEntity.ok(MapperTask.toGetTaskResponse(taskService.getTask(getUser().getUsername(), pageNumber, pageSize)));
    }

    @PutMapping()
    public ResponseEntity<UpdateTaskResponse> updateTask(@RequestBody UpdateTaskRequest request) {
        return ResponseEntity.ok(MapperTask.toUpdateTaskResponse(taskService.update(MapperTask.toTask(request))));
    }


}
