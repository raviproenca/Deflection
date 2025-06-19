package org.javasource.services;

import org.javasource.config.PredictionRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;


@Service
public class PythonMLService {

    private final WebClient webClient;

    public PythonMLService(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl("http://localhost:8000").build();
    }

    public String callPython(PredictionRequest request) {
        return webClient.post()
                .uri("/ml/predict")
                .body(Mono.just(request), PredictionRequest.class)
                .retrieve()
                .bodyToMono(String.class)
                .block();

    }

    private record RequestData(String text) {}
}
