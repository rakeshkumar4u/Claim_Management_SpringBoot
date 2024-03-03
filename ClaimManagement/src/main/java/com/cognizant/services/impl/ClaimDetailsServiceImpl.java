package com.cognizant.services.impl;

import java.time.Year;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cognizant.dto.ClaimDetailsDto;
import com.cognizant.entities.ClaimDetails;
import com.cognizant.exception.MaximumClaimLimitReachedException;
import com.cognizant.repository.ClaimDetailsRepo;
import com.cognizant.services.ClaimDetailsService;

@Service
public class ClaimDetailsServiceImpl implements ClaimDetailsService {
	
	
	private final ClaimDetailsRepo claimDetailsRepo;
    private final ModelMapper modelMapper;
 
    @Autowired
    public ClaimDetailsServiceImpl(ClaimDetailsRepo claimDetailsRepo, ModelMapper modelMapper) {
        this.claimDetailsRepo = claimDetailsRepo;
        this.modelMapper = modelMapper;
    }

	@Override
    public ClaimDetailsDto insertClaim(ClaimDetailsDto claimDetailsDto) throws MaximumClaimLimitReachedException {
        // Generate Claim ID
        String policyNo = claimDetailsDto.getPolicyNo();
        int year = Year.now().getValue();
        String claimId = "CL" + policyNo.substring(policyNo.length() - 4) + year;
 
        // Validate Policy No and Claim Limit
        if (claimDetailsRepo.existsByPolicyNoAndDateOfAccidentYear(policyNo, year)) {
            throw new MaximumClaimLimitReachedException("Maximum claim limit reached for policy number " + policyNo + " in " + year);
        }
 
        // Save Claim Details
        ClaimDetails claimDetails = modelMapper.map(claimDetailsDto, ClaimDetails.class);
        claimDetails.setClaimId(claimId);
        claimDetailsRepo.save(claimDetails);
 
        // Convert to ClaimDetailsDto and return
        return modelMapper.map(claimDetails, ClaimDetailsDto.class);
    }

	@Override
	public void updateClaim(String claimId,ClaimDetailsDto claimDetailsDto) {
		   ClaimDetails existingClaim = claimDetailsRepo.findById(claimId)
	                .orElseThrow(() -> new RuntimeException("Claim not found"));
	 
	        
	        modelMapper.map(claimDetailsDto, existingClaim);
	 
	       
	        claimDetailsRepo.save(existingClaim);
	    }
	
	@Override
	public long countPendingClaims(int month, int year) {
	    return claimDetailsRepo.countByClaimStatusAndDateOfAccidentYearAndDateOfAccidentMonth(false, year, month);
	}
	
	@Override
	public double totalApprovedAmountByInsuranceCompany(int month, int year) {
	    return claimDetailsRepo.sumAmtApprovedByInsuranceCompanyByMonthAndYear(year, month);
	}
	
	
	

}
