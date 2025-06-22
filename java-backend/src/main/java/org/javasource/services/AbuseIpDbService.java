package org.javasource.services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriBuilder;

@Service
public class AbuseIpDbService {

    private final WebClient webClient;

    public AbuseIpDbService(@Value("${abuseipdb.api.key}") String apiKey) {
        this.webClient = WebClient.builder()
                .baseUrl("https://api.abuseipdb.com")
                .defaultHeader("Key", apiKey)
                .defaultHeader("Accept", "application/json")
                .build();
    }

    public String checkIp(String ip) {
        return webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/api/v2/check")
                        .queryParam("ipAddress", ip)
                        .queryParam("maxAgeInDays", 90)
                        .build())
                .retrieve()
                .bodyToMono(String.class)
                .block();
    }
}
