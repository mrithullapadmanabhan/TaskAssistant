package com.project.taskassistant.model;

public record Choice(
        int index,
        Message message,
        String finish_reason
) {}
