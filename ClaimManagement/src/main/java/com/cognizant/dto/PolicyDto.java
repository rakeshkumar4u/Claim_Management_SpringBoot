package com.cognizant.dto;

import java.time.LocalDate;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PolicyDto {
	
	private String policyNo;
	
	@Size(min=5,message="First Name should be of min 5 character")
	private String insuredFirstName;
	
	@Size(min=5,message="Last Name should be of min 5 character")
	private String insuredLastName;
    
	@PastOrPresent(message="Date of insurance must not be earlier than current date")
	private LocalDate dateOfInsurance;
 
	@Email
	private String emailId;

	private String vehicleNo;

	private boolean status;

}


