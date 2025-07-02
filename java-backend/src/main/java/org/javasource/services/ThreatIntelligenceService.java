package org.javasource.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.javasource.models.dto.*;
import org.javasource.models.dto.abuseIPDB.AbuseipdbDTO;
import org.javasource.models.dto.ipAnalysis.IpAnalysisAssembler;
import org.javasource.models.dto.ipAnalysis.IpAnalysisDTO;
import org.javasource.models.dto.virusTotal.VirusTotalDTO;
import org.javasource.services.apis.AbuseIpDbService;
import org.javasource.services.apis.GreyNoiseService;
import org.javasource.services.apis.VirusTotalService;
import org.springframework.stereotype.Service;
import java.lang.String;

@Slf4j
@RequiredArgsConstructor
@Service
public class ThreatIntelligenceService {

    private final AbuseIpDbService abuseIpDbService;
    private final GreyNoiseService greyNoiseService;
    private final VirusTotalService virusTotalService;

    public IpAnalysisDTO checkIpAcrossAll(String ip) {
         VirusTotalDTO virusTotalResult = virusTotalService.checkIp(ip);
         AbuseipdbDTO abuseIpDbResult = abuseIpDbService.checkIp(ip);
         GreyNoiseDTO greyNoiseResult = greyNoiseService.checkIp(ip);

         return IpAnalysisAssembler.assemble(virusTotalResult, abuseIpDbResult, greyNoiseResult);
    }
}
