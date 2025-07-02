package org.javasource.models.dto.virusTotal;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record VirusTotalDataDTO(
    String id,
    String type,
    VirusTotalAttributesDTO attributes
){}
