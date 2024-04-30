package com.cognizant.utilities;

import java.time.LocalDate;

public class ClaimIdGenerator{
public static String generateClaimId(String policyNo, LocalDate dateOfAccident) {
    if (policyNo == null || dateOfAccident == null) {
        throw new IllegalArgumentException("Input parameters cannot be null");
    }
    
    //  regular expression \\D represents any character that is not a digit.
    String policyDigits = policyNo.replaceAll("\\D+", "").substring(0, 4); 

  //formats the year as a 4-digit string with zeros if required
    String yearDigits = String.format("%04d", dateOfAccident.getYear()); 
    String claimId = "CL" + policyDigits + yearDigits;
    
    return claimId;
}
}


