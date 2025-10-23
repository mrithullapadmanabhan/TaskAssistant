package com.project.taskassistant.controller;

import com.project.taskassistant.service.InsightService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/ai")
@RequiredArgsConstructor
public class InsightController {

    private final InsightService insightService;

    @PostMapping("/suggest")
    public ResponseEntity<String> suggest(@RequestBody Map<String, String> body) {
        String prompt = body.get("prompt");
        String response = insightService.getTextSuggestion(prompt);
        return ResponseEntity.ok(response);
    }
}


