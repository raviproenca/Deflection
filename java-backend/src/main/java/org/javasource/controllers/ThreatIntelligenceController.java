package org.javasource.controllers;

import lombok.RequiredArgsConstructor;
import org.javasource.models.dto.ipAnalysis.IpAnalysisDTO;
import org.javasource.models.dto.PredictionResponse;
import org.javasource.services.PythonMLService;
import org.javasource.services.ThreatIntelligenceService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/threats")
public class ThreatIntelligenceController {

    private final ThreatIntelligenceService threatIntelligenceService;
    private final PythonMLService pythonMLService;

    @GetMapping("/check/{ip}")
    public ResponseEntity<PredictionResponse> checkIp(@PathVariable String ip) {
        IpAnalysisDTO result = threatIntelligenceService.checkIpAcrossAll(ip);
        PredictionResponse prediction = pythonMLService.callPython(result);
        return ResponseEntity.ok(prediction);
    }
}


