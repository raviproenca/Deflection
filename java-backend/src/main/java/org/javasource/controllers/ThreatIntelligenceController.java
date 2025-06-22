package org.javasource.controllers;

import org.javasource.models.dto.ThreatIntelligenceRequest;
import org.javasource.services.ThreatIntelligenceService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/threats")
public class ThreatIntelligenceController {

    private final ThreatIntelligenceService threatIntelligenceService;

    public ThreatIntelligenceController (ThreatIntelligenceService threatIntelligenceService) {
        this.threatIntelligenceService = threatIntelligenceService;
    }

    @GetMapping("/check/{ip}")
    public List<String> checkIp(@PathVariable String ip) {
        return threatIntelligenceService.checkIpAcrossAll(ip);
    }
}
