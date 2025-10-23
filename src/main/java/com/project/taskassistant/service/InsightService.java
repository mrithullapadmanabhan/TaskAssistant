package com.project.taskassistant.service;

import com.project.taskassistant.gateway.AiModelGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class InsightService {

    private final AiModelGateway aiModelGateway;

    public String getTextSuggestion(String text) {
        return aiModelGateway.getOpenAiCompletionsForText(text).block();
    }

}
