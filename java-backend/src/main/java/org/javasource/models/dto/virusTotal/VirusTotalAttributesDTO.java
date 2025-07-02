package org.javasource.models.dto.virusTotal;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record VirusTotalAttributesDTO(
        int asn,
        String country,
        String network,
        LastAnalysisStats last_analysis_stats,
        String as_owner,
        int reputation,
        String continent
) {}
