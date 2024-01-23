package com.task.domain;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Task {
    private Integer id;
    private String title;
    private Integer toUser;
    private Integer fromUser;
    private boolean state;
}
