package com.project.taskassistant.model;

import java.util.List;

public record ChatResponse(
        List<Choice> choices
) {}

