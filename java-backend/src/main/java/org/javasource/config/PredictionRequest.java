package org.javasource.config;

import lombok.Data;

@Data
public class PredictionRequest {
    private String ip;
    private int login_attempts;
    private String country;
    private String time_of_day;
}