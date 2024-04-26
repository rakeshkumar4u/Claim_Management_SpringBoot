package com.cognizant.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.cognizant.entities.ClaimDetails;
@Repository
public interface ClaimDetailsRepo extends JpaRepository<ClaimDetails,String> {
	
	@Query("SELECT cd FROM ClaimDetails cd JOIN cd.policy p WHERE p.policyNo = :policyId AND YEAR(cd.dateOfAccident) = :year")
    List<ClaimDetails> findByPolicyNoAndDateOfAccidentYear(@Param("policyId") String policyId, @Param("year") int year);

	  @Query("SELECT COUNT(cd) FROM ClaimDetails cd WHERE cd.claimStatus = :claimStatus AND YEAR(cd.dateOfAccident) = :year AND MONTH(cd.dateOfAccident) = :month")
	    int countByClaimStatusAndDateOfAccidentYearMonth(@Param("claimStatus") boolean claimStatus, @Param("year") int year, @Param("month") int month);
	 
	  @Query("SELECT cd.amtApprovedBySurveyor FROM ClaimDetails cd WHERE cd.insuranceCompanyApproval = :insuranceCompanyApproval AND YEAR(cd.dateOfAccident) = :year AND MONTH(cd.dateOfAccident) = :month")
	  int getApprovedAmountBySurveyorByInsuranceCompanyApprovalAndDateOfAccidentYearMonth(@Param("insuranceCompanyApproval") boolean insuranceCompanyApproval, @Param("year") int year, @Param("month") int month);
	  
	  List<ClaimDetails> findByEstimatedLoss(int estimatedLoss);
}
