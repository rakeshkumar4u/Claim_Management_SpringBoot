package com.cognizant.utilities;
 
import java.time.LocalDate;
 
public class PolicyIdGenerator {
	 
    public static String generatePolicyId(String insuredLastName, String vehicleNo, LocalDate dateOfInsurance) {
        String firstTwoLetters = insuredLastName.substring(0, Math.min(insuredLastName.length(), 2)).toUpperCase();
        String vehicleNumber = extractDigits(vehicleNo);
        int year = dateOfInsurance.getYear() % 100;
       // Format the PolicyId
        String policyId = String.format("%s%s%02d", firstTwoLetters, vehicleNumber, year);       
        return policyId;
    }
 
    private static String extractDigits(String input) {
        StringBuilder digits = new StringBuilder();
        for (char c : input.toCharArray()) {
            if (Character.isDigit(c)) {
                digits.append(c);
                if (digits.length() == 3) { // Stop when 3 digits are extracted
                    break;
                }
            }
        }
        // If fewer than 3 digits are found, add zeros
        while (digits.length() < 3) {
            digits.insert(0, '0');
        }
        return digits.toString();
    }
}
