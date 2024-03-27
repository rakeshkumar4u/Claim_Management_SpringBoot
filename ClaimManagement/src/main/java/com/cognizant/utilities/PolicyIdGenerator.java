package com.cognizant.utilities;
 
import java.sql.Date;
import java.time.LocalDate;
 
public class PolicyIdGenerator {
    public static String generatePolicyId(String insuredLastName, String vehicleNo, LocalDate date) {

    	 if (insuredLastName == null || vehicleNo == null || date == null) {
             throw new IllegalArgumentException("Input parameters cannot be null");
         }
        String firstTwoLetters = insuredLastName.substring(0, 2).toUpperCase();
        String numericVehicleNo = vehicleNo.replaceAll("[^0-9]", "");
 
        // Handle null or empty vehicle number
        int parsedVehicleNo = numericVehicleNo.isEmpty() ? 0 : Integer.parseInt(numericVehicleNo);
        String formattedVehicleNo = String.format("%03d", parsedVehicleNo);
 
        int year = date.getYear() % 100; // Get last 2 digits of the year
 
        return firstTwoLetters + formattedVehicleNo + String.format("%02d", year);
    }
}


