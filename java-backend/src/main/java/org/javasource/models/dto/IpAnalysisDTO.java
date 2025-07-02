package org.javasource.models.dto;

import lombok.Data;

@Data
public class IpAnalysisDTO {
    private String ip;

    private boolean noise;
    private boolean riot;
    private String classification;

    private int abuseConfidenceScore;
    private int totalReports;
    private int numDistinctUsers;

    private int reputation;
    private int malicious;
    private int suspicious;
    private int undetected;
    private int harmless;

    private String country;
    private String asOwner;
    private int asn;
}
