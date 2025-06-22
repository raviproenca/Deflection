package org.javasource.services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class GreyNoiseService {

    private final WebClient webClient;

    public GreyNoiseService(@Value("${greynoise.api.key}") String apiKey) {
        this.webClient = WebClient.builder()
                .baseUrl("api.greynoise.io/v3/community")
                .defaultHeader("key", apiKey)
                .defaultHeader("accept", "application/json")
                .build();
    }

    public String checkIp(String ip) {
        return webClient.get()
                .uri("/{ip}", ip)
                .retrieve()
                .bodyToMono(String.class)
                .block();
    }
}
