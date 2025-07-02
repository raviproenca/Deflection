package org.javasource.services;

import lombok.extern.slf4j.Slf4j;
import org.javasource.exceptions.ExternalApiException;
import org.javasource.models.dto.ipAnalysis.IpAnalysisDTO;
import org.javasource.models.dto.PredictionResponse;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Slf4j
@Service
public class PythonMLService {

    private final WebClient webClient;

    public PythonMLService(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl("http://localhost:8000").build();
    }

    public PredictionResponse callPython(IpAnalysisDTO request) {
        try {
            return webClient.post()
                    .uri("/ml/predict")
                    .body(Mono.just(request), IpAnalysisDTO.class)
                    .retrieve()
                    .onStatus(HttpStatusCode::is4xxClientError,
                            response -> response.bodyToMono(String.class)
                                    .map(body -> new ExternalApiException("Client error (4xx): " + body)))
                    .onStatus(HttpStatusCode::is5xxServerError,
                            response -> response.bodyToMono(String.class)
                                    .map(body -> new ExternalApiException("Server error (5xx): " + body)))
                    .bodyToMono(PredictionResponse.class)
                    .block();
        } catch (ExternalApiException e) {
            log.error("Python error: {}", e.getMessage());
            throw e;
        } catch (Exception e) {
            log.error("Erro when calling Python ML", e);
            throw new ExternalApiException("Error when calling Python ML", e);
        }
    }
}
