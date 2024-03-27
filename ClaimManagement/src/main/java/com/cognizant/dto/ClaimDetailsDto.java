package com.cognizant.dto;

import java.time.LocalDate;

import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClaimDetailsDto {
	
	@Size(min=10,max=10,message="size must be between 10 and 10")
	private String claimId;

	private String policyNo;
	@Min(value=0,message="The value cannot be negative")
	private int estimatedLoss;
	
	@PastOrPresent
	private LocalDate dateOfAccident;

	@AssertTrue(message="ClaimStatus must be either closed(true) or open (false.")
	private boolean claimStatus;
	
	@Min(value=0,message="The value cannot be negative")
	private int amtApprovedBySurveyor;
    
	private boolean insuranceCompanyApproval=false;
    
	private boolean withdrawClaim=false;

	private int surveyorFees;

}
