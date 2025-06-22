package org.javasource.models.dto;
import lombok.Data;

@Data
public class ThreatIntelligenceResponse {
    private String source;           // VirusTotal, AbuseIPDB, GreyNoise etc.
    private String ip;
    private String reputation;       // Good, suspicious, malicious
    private String threatLevel;      // High, Medium, Low
    private String description;
    private String recommendation;   // Block, Monitor, Safe

    public ThreatIntelligenceResponse(String source, String ip, String reputation, String threatLevel,
                               String description, String recommendation) {
        this.source = source;
        this.ip = ip;
        this.reputation = reputation;
        this.threatLevel = threatLevel;
        this.description = description;
        this.recommendation = recommendation;
    }
}
