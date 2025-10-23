package com.project.taskassistant.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TaskRequestDTO {
    private String title;
    private String description;
    private TaskCategory category;
    private TaskStatus status;
    private String userId;
}
