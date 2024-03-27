package com.cognizant.services;

import java.util.List;

import com.cognizant.dto.ClaimDetailsDto;

public interface ClaimService {
	
	ClaimDetailsDto insertClaim(ClaimDetailsDto claimDetailsDto);
	
	ClaimDetailsDto updateClaim(ClaimDetailsDto claimDetailsDto,String claimId);
	
	public List<ClaimDetailsDto> getAllClaims();
	
}

