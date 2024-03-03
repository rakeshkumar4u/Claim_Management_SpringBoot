package com.cognizant.entities;

import java.sql.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;


@Entity
@Data

public class ClaimDetails{
	
	@Column(name="Claim_Id")
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private String ClaimId;
	
	@Column(name="Policy_No")
	private String PolicyNo;

	
	@Column(name="Estimated_Loss")
	private Integer EstimatedLoss;
	
	@Column(name="Date_Of_Accident")
	private Date DateOfAccident;
	
	@Column(name="Claim_Status")
	private Boolean ClaimStatus;
	
	@Column(name="Surveyor_Id")
	private Integer SurveyorId;
	
	@Column(name="Amt_Approved_By_Surveyor")
	private Integer AmtApprovedBySurveyor;
	
	@Column(name="Insurance_Company_Approval")
	private Boolean InsuranceCompanyApproval;
	
	@Column(name="Withdraw_Claim")
	private Boolean WithdrawClaim;
	
	@Column(name="Surveyor_Fees")
	private Integer SurveyorFees;	
	
}