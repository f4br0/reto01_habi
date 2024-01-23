package com.task.controller.task.dto;


import com.task.domain.Task;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class NewTaskResponse  {
    private Integer id;
}
