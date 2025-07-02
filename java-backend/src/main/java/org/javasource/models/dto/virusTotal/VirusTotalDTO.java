package org.javasource.models.dto.virusTotal;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record VirusTotalDTO(
        VirusTotalDataDTO data
) {}


