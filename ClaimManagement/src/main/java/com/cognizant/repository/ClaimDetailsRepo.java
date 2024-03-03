package com.cognizant.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.cognizant.entities.ClaimDetails;

//@Repository
public interface ClaimDetailsRepo extends JpaRepository<ClaimDetails,String> {

	Optional<ClaimDetails> findById(String claimId);

	boolean existsByPolicyNoAndDateOfAccidentYear(String policyNo, int year);

		@Query(value = "SELECT COUNT(cd) FROM ClaimDetails cd "
            + "WHERE cd.ClaimStatus = ?1 AND YEAR(cd.DateOfAccident) = ?2 AND MONTH(cd.dateOfAccident) = ?3")
    public long countByClaimStatusAndDateOfAccidentYearAndDateOfAccidentMonth(boolean ClaimStatus, int year, int month);
	
	
	
	@Query(value = "SELECT SUM(AmtApprovedBySurveyor) FROM ClaimDetails cd "
            + "WHERE cd.insuranceCompanyApproval = true AND MONTH(cd.DateOfAccident) = ?1 AND YEAR(cd.DateOfAccident) = ?2", nativeQuery = true)
    public double sumAmtApprovedByInsuranceCompanyByMonthAndYear(int month, int year);

}
