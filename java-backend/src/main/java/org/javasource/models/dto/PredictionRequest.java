package org.javasource.models.dto;

import lombok.Data;

@Data
public class PredictionRequest {
    private String ip;
    private int loginAttempts;
    private String country;
    private String timeOfDay;
}