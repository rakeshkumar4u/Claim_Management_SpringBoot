package com.cognizant.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cognizant.dto.PolicyDto;
import java.util.*;

import com.cognizant.services.PolicyService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@CrossOrigin(origins="http://localhost:4200")
@RequestMapping("/api/policies")
@Tag(name="Policy Controller", description="Policy Controller REST API")
public class PolicyController{
 
    @Autowired
    private PolicyService policyService;
 
    @GetMapping("/")
    @Operation(description = "Retrieve All Resources of Type Policy")
    public ResponseEntity<List< PolicyDto>> getAllPolicies() {
        List<PolicyDto> policyDtos = policyService.getAllPolicies();
        return ResponseEntity.ok(policyDtos);
    }
    @PostMapping("/")
    @Operation(description = "Create New Policy")
	public ResponseEntity<PolicyDto>insertPolicy(@Valid @RequestBody PolicyDto policyDto)
	{
    	PolicyDto insertedPolicyDto=this.policyService.insertPolicy(policyDto);
		return new ResponseEntity<>(insertedPolicyDto,HttpStatus.CREATED);
	}
	
}