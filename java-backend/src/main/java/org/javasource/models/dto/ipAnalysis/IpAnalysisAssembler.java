package org.javasource.models.dto.ipAnalysis;

import org.javasource.models.dto.GreyNoiseDTO;
import org.javasource.models.dto.abuseIPDB.AbuseipdbDTO;
import org.javasource.models.dto.virusTotal.VirusTotalDTO;

public class IpAnalysisAssembler {
    public static IpAnalysisDTO assemble(VirusTotalDTO vt, AbuseipdbDTO ab, GreyNoiseDTO gn) {
        IpAnalysisDTO dto = new IpAnalysisDTO();

        dto.setIp(vt.data().id());
        dto.setReputation(vt.data().attributes().reputation());
        dto.setMalicious(vt.data().attributes().last_analysis_stats().malicious());
        dto.setSuspicious(vt.data().attributes().last_analysis_stats().suspicious());
        dto.setUndetected(vt.data().attributes().last_analysis_stats().undetected());
        dto.setHarmless(vt.data().attributes().last_analysis_stats().harmless());
        dto.setAsn(vt.data().attributes().asn());
        dto.setAsOwner(vt.data().attributes().as_owner());
        dto.setCountry(vt.data().attributes().country());

        dto.setAbuseConfidenceScore(ab.data().abuseConfidenceScore());
        dto.setTotalReports(ab.data().totalReports());
        dto.setNumDistinctUsers(ab.data().numDistinctUsers());

        dto.setNoise(gn.noise());
        dto.setRiot(gn.riot());
        dto.setClassification(gn.classification());

        return dto;
    }
}
