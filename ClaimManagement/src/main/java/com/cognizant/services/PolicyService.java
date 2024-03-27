package com.cognizant.services;

import java.util.List;

import com.cognizant.dto.PolicyDto;

public interface PolicyService {
	
	PolicyDto insertPolicy(PolicyDto policyDto);
	public List<PolicyDto> getAllPolicies();

}
