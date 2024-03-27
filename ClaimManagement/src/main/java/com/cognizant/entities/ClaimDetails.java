package com.cognizant.entities;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
 
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClaimDetails {
 
	@Id
	@Column(name="Claim_Id")
    private String claimId;
	
    @ManyToOne
    @JoinColumn(name = "Policy_No", referencedColumnName = "Policy_No")
    private Policy policy;
 
    @Column(name = "Estimated_Loss")
    private int estimatedLoss;
    
    @Column(name = "Date_Of_Accident")
    private LocalDate dateOfAccident;
 
    @Column(name = "Claim_Status")
    private boolean claimStatus;
 
    @ManyToOne
    @JoinColumn(name = "Surveyor_Id", referencedColumnName = "Surveyor_Id")
    private Surveyor surveyor;
 
    @Column(name = "Amt_Approved_By_Surveyor")
    private int amtApprovedBySurveyor;
 
    @Column(name = "Insurance_Company_Approval")
    private boolean insuranceCompanyApproval;
 
    @Column(name = "Withdraw_Claim")
    private boolean withdrawClaim;
 
    @Column(name = "Surveyor_Fees")
    private int surveyorFees;
    
}