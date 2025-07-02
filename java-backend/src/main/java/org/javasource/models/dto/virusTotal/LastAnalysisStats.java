package org.javasource.models.dto.virusTotal;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record LastAnalysisStats(
        int harmless,
        int malicious,
        int suspicious,
        int undetected,
        int timeout
) {}