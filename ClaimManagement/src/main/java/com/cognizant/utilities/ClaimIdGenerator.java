package com.cognizant.utilities;

import java.time.LocalDate;

public class ClaimIdGenerator{
public static String generateClaimId(String policyNo, LocalDate dateOfAccident) {
    if (policyNo == null || dateOfAccident == null) {
        throw new IllegalArgumentException("Input parameters cannot be null");
    }
 
    String policyDigits = policyNo.replaceAll("\\D+", "").substring(0, 4); // Extract 4 digits from policyNo
    String yearDigits = String.format("%04d", dateOfAccident.getYear()); // Get 4-digit year
    String claimId = "CL" + policyDigits + yearDigits;
    
    return claimId;
}
}