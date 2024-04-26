package com.cognizant.services;

public interface ClaimReportService {	
    int getPendingClaimsCount(int month,int year);	
	int getApprovedAmountByInsuranceCompany(int month,int year);

}
