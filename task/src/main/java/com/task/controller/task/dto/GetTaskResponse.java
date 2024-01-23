package com.task.controller.task.dto;

import com.task.domain.Task;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class GetTaskResponse {
    private List<Task> tasks;
    private Long totalItems;
}
