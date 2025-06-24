package org.javasource.services.apis;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class VirusTotalService {

    private final WebClient webClient;

    public VirusTotalService(@Value("${virustotal.api.key}") String apiKey) {
        this.webClient = WebClient.builder()
                .baseUrl("https://www.virustotal.com/api/v3")
                .defaultHeader("x-apikey", apiKey)
                .defaultHeader("Accept", "application/json")
                .build();

    }

    public String checkIp(String ip) {
        return webClient.get()
                .uri("/ip_addresses/{ip}", ip)
                .retrieve()
                .bodyToMono(String.class)
                .block();
    }
}
