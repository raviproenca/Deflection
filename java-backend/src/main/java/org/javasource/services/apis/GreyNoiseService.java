package org.javasource.services.apis;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.javasource.exceptions.ExternalApiException;
import org.javasource.models.dto.GreyNoiseDTO;
import org.slf4j.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.time.Duration;

@Slf4j
@Service
public class GreyNoiseService {
    private final WebClient webClient;
    private final Duration timeout = Duration.ofSeconds(10);

    public GreyNoiseService(@Value("${greynoise.api.key}") String apiKey) {
        this.webClient = WebClient.builder()
                .baseUrl("https://api.greynoise.io/v3/community")
                .defaultHeader("key", apiKey)
                .defaultHeader("accept", "application/json")
                .build();
    }

    public GreyNoiseDTO checkIp(String ip) {
        if (ip == null || ip.isBlank()) {
            throw new IllegalArgumentException("IP can't be null or empty.");
        }

        try {
            return webClient.get()
                    .uri("/{ip}", ip)
                    .retrieve()
                    .onStatus(HttpStatusCode::is4xxClientError,
                            response -> response.bodyToMono(String.class)
                                    .map(body -> new ExternalApiException("Client error (4xx): " + body)))
                    .onStatus(HttpStatusCode::is5xxServerError,
                            response -> response.bodyToMono(String.class)
                                    .map(body -> new ExternalApiException("Server error (5xx): " + body)))
                    .bodyToMono(String.class)
                    .doOnNext(json -> log.info("GreyNoise RAW response: {}", json))
                    .map(json -> {
                        try {
                            ObjectMapper mapper = new ObjectMapper();
                            return mapper.readValue(json, GreyNoiseDTO.class);
                        } catch (Exception e) {
                            log.error("Erro ao desserializar JSON do GreyNoise: {}", e.getMessage(), e);
                            throw new ExternalApiException("Erro ao desserializar resposta da GreyNoise", e);
                        }
                    })
                    .block(timeout);

        } catch (ExternalApiException e) {
            log.error("GreyNoise error to IP {}: {}", ip, e.getMessage());
            throw e;
        } catch (Exception e) {
            log.error("Error when consulting GreyNoise API to IP: {}", ip, e);
            throw new ExternalApiException("Error when consulting GreyNoise API", e);
        }
    }
}
