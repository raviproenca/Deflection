package org.javasource.models.dto;

import lombok.extern.slf4j.Slf4j;

public record GreyNoiseDTO(
        String ip,
        boolean noise,
        boolean riot,
        String classification,
        String name,
        String link,
        String last_seen,
        String message
) {}
