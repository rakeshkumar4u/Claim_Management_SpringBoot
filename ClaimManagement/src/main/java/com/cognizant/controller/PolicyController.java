package com.cognizant.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cognizant.dto.PolicyDto;
import java.util.*;

import com.cognizant.services.PolicyService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/policies")
public class PolicyController{
 
    @Autowired
    private PolicyService policyService;
 
    @GetMapping("/")
    public ResponseEntity<List< PolicyDto>> getAllPolicies() {
        List<PolicyDto> policyDtos = policyService.getAllPolicies();
        return ResponseEntity.ok(policyDtos);
    }
    @PostMapping("/")
	public ResponseEntity<PolicyDto>insertPolicy(@Valid @RequestBody PolicyDto policyDto)
	{
    	PolicyDto insertedPolicyDto=this.policyService.insertPolicy(policyDto);
		return new ResponseEntity<>(insertedPolicyDto,HttpStatus.CREATED);
	}
	
}