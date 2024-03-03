package com.cognizant.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cognizant.dto.ClaimDetailsDto;
import com.cognizant.services.ClaimDetailsService;

@RestController
@RequestMapping("/api/claimdetails")
public class ClaimDetailsController {
	
	@Autowired
	private ClaimDetailsService claimDetailsService;
	
	@PostMapping("/")
	public ResponseEntity<ClaimDetailsDto> insertClaim(@RequestBody ClaimDetailsDto claimDetailsDto){
		ClaimDetailsDto insertedClaimDetailsDto=this.claimDetailsService.insertClaim(claimDetailsDto);
		return new ResponseEntity<>(insertedClaimDetailsDto,HttpStatus.CREATED);	
	 }
	
	@PutMapping("/{claimId}")
	public ResponseEntity<String> updateClaim(@PathVariable String claimId,@RequestBody ClaimDetailsDto claimDetailsDto)
	{
		claimDetailsService.updateClaim(claimId,claimDetailsDto);
		
		return ResponseEntity.ok("Updated");
		
	}
	
	
	
	

}
