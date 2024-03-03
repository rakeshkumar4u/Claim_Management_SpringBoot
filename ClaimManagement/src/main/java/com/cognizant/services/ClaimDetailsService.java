package com.cognizant.services;

import com.cognizant.dto.ClaimDetailsDto;

public interface ClaimDetailsService {
	
	public ClaimDetailsDto insertClaim(ClaimDetailsDto claimDetailsDto);
	
	void updateClaim(String claimId,ClaimDetailsDto claimDetailsDto);
	
	public long countPendingClaims(int month,int year);
	
	public double totalApprovedAmountByInsuranceCompany(int month,int year);
	
	
	

}
