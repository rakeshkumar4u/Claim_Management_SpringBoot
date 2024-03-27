package com.cognizant.services;

public interface ClaimReportService {
	
	int getPendingClaimsCount(int month,int year);
	
	double getApprovedAmountByInsuranceCompany(int month,int year);

}
