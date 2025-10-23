package com.project.taskassistant.gateway;

import com.project.taskassistant.model.ChatResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class AiModelGateway {

    private final WebClient.Builder webClientBuilder;

    @Value("${groq.api.key}")
    private String apiKey;

    private static final String GROQ_URL = "https://api.groq.com/openai/v1/chat/completions";

    public Mono<String> getOpenAiCompletionsForText(String prompt) {
        System.out.println("API Key loaded: " + apiKey);

        return webClientBuilder
                .baseUrl(GROQ_URL)
                .defaultHeader(HttpHeaders.AUTHORIZATION, "Bearer " + apiKey)
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .build()
                .post()
                .bodyValue(Map.of(
                        "model", "llama-3.3-70b-versatile",
                        "messages", List.of(Map.of("role", "system", "content", "You are a helpful writing assistant that rephrases text clearly and naturally."),
                                Map.of("role", "user", "content", "Rephrase this: "+prompt))
                ))
                .retrieve()
                .bodyToMono(ChatResponse.class)
                .map(response -> response.choices().getFirst().message().content());
    }
}
