package com.cognizant.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cognizant.repository.ClaimDetailsRepo;
import com.cognizant.services.ClaimReportService;

@Service
public class ClaimReportServiceImpl implements ClaimReportService {
 
    private final ClaimDetailsRepo claimDetailsRepo;
    
    @Autowired
    public ClaimReportServiceImpl(ClaimDetailsRepo claimDetailsRepo)
    {
    	this.claimDetailsRepo=claimDetailsRepo;
    }
    
    @Override
    public int getPendingClaimsCount(int month, int year) {
        return claimDetailsRepo.countByClaimStatusAndDateOfAccidentYearMonth(false, year, month);
    }
 
    @Override
    public int getApprovedAmountByInsuranceCompany(int month, int year) {
        return claimDetailsRepo.getApprovedAmountBySurveyorByInsuranceCompanyApprovalAndDateOfAccidentYearMonth(true, year, month);
    }
}
 




