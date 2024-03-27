package com.cognizant.utilities;

import java.time.LocalDate;

public class ClaimIdGenerator {
    public static String generateClaimId(String policyNo) {
        String policyNumber = policyNo.substring(2, 7);
 
        String yearDigits = String.valueOf(LocalDate.now().getYear() % 100);
        if (yearDigits.length() == 1) {
            yearDigits = "0" + yearDigits;
        }
        return "CL" + policyNumber + yearDigits;
    }
}
