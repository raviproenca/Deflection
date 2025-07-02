package org.javasource.models.dto.abuseIPDB;

import java.util.List;

public record AbuseipdbDataDTO(
        String ipAddress,
        boolean isPublic,
        int ipVersion,
        boolean isWhitelisted,
        int abuseConfidenceScore,
        String countryCode,
        String usageType,
        String isp,
        String domain,
        List<String> hostnames,
        boolean isTor,
        int totalReports,
        int numDistinctUsers,
        String lastReportedAt
) {}
