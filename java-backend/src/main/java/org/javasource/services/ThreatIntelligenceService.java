package org.javasource.services;

import org.javasource.services.apis.AbuseIpDbService;
import org.javasource.services.apis.GreyNoiseService;
import org.javasource.services.apis.VirusTotalService;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.lang.String;

@Service
public class ThreatIntelligenceService {

    private final AbuseIpDbService abuseIpDbService;
    private final GreyNoiseService greyNoiseService;
    private final VirusTotalService virusTotalService;

    public ThreatIntelligenceService(AbuseIpDbService abuseIpDbService,
                                     GreyNoiseService greyNoiseService,
                                     VirusTotalService virusTotalService) {
        this.abuseIpDbService = abuseIpDbService;
        this.greyNoiseService = greyNoiseService;
        this.virusTotalService = virusTotalService;
    }

    public List<String> checkIpAcrossAll(String ip) {
         String virusTotalResult = virusTotalService.checkIp(ip);
         String abuseIpDbResult = abuseIpDbService.checkIp(ip);
         String greyNoiseResult = greyNoiseService.checkIp(ip);

         return Arrays.asList(virusTotalResult, abuseIpDbResult, greyNoiseResult);

    }
}
