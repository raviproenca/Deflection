package org.javasource.models.dto;

import lombok.Data;

@Data
public class PredictionResponse {
    private String ip;
    private boolean noise;
    private boolean riot;
    private int score;
    private String threat_level;
    private String recommendation;
}