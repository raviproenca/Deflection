package org.javasource.controllers;

import lombok.RequiredArgsConstructor;
import org.javasource.models.dto.IpAnalysisDTO;
import org.javasource.services.ThreatIntelligenceService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/threats")
public class ThreatIntelligenceController {

    private final ThreatIntelligenceService threatIntelligenceService;

    @GetMapping("/check/{ip}")
    public ResponseEntity<IpAnalysisDTO> checkIp(@PathVariable String ip) {
        IpAnalysisDTO result = threatIntelligenceService.checkIpAcrossAll(ip);
        return ResponseEntity.ok(result);
    }
}
