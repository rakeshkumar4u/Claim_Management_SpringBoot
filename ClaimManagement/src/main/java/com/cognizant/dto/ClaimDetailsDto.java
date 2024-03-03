package com.cognizant.dto;

import java.sql.Date;

import org.springframework.beans.factory.annotation.Value;

import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class ClaimDetailsDto {
	
	@Size(min=10)
	private String ClaimId;

	private String PolicyNo;
	
	@PositiveOrZero
	private Integer EstimatedLoss;
	
	private Date DateOfAccident;

	private Boolean ClaimStatus;

	private Integer SurveyorId;
	
	@PositiveOrZero
	private Integer AmtApprovedBySurveyor;
    
	@Value("${insurance.company.approval:false}")
	private Boolean InsuranceCompanyApproval;
    
	@Value("${withdraw.claim:false}")
	private Boolean WithdrawClaim;

	private Integer SurveyorFees;

	
}
