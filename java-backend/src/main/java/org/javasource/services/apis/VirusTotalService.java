package org.javasource.services.apis;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.javasource.exceptions.ExternalApiException;
import org.javasource.models.dto.virusTotal.VirusTotalDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.time.Duration;

@Slf4j
@Service
public class VirusTotalService {
    private final WebClient webClient;
    private final Duration timeout = Duration.ofSeconds(10);

    public VirusTotalService(@Value("${virustotal.api.key}") String apiKey) {
        this.webClient = WebClient.builder()
                .baseUrl("https://www.virustotal.com/api/v3")
                .defaultHeader("x-apikey", apiKey)
                .defaultHeader("Accept", "application/json")
                .build();
    }

    public VirusTotalDTO checkIp(String ip) {
        if (ip == null || ip.isBlank()) {
            throw new IllegalArgumentException("IP can't be null or empty.");
        }

        try {
            return webClient.get()
                    .uri("/ip_addresses/{ip}", ip)
                    .retrieve()
                    .onStatus(HttpStatusCode::is4xxClientError,
                            response -> response.bodyToMono(String.class)
                                    .map(body -> new ExternalApiException("Client error (4xx): " + body)))
                    .onStatus(HttpStatusCode::is5xxServerError,
                            response -> response.bodyToMono(String.class)
                                    .map(body -> new ExternalApiException("Server error (5xx): " + body)))
                    .bodyToMono(String.class)
                    .doOnNext(json -> log.info("VirusTotal RAW response: {}", json))
                    .map(json -> {
                        try {
                            ObjectMapper mapper = new ObjectMapper();
                            return mapper.readValue(json, VirusTotalDTO.class);
                        } catch (Exception e) {
                            log.error("Erro ao desserializar JSON do VirusTotal: {}", e.getMessage(), e);
                            throw new ExternalApiException("Erro ao desserializar resposta da VirusTotal", e);
                        }
                    })
                    .block(timeout);
        } catch (ExternalApiException e) {
            log.error("VirusTotal error to IP {}: {}", ip, e.getMessage());
            throw e;
        } catch (Exception e) {
            log.error("Error when consulting VirusTotal API to IP: {}", ip, e);
            throw new ExternalApiException("Error when consulting VirusTotal API", e);
        }
    }
}
