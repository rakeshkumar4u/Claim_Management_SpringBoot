package com.cognizant.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cognizant.dto.ClaimDetailsDto;
import com.cognizant.services.ClaimService;

import jakarta.validation.Valid;

@RestController
@CrossOrigin(origins="http://localhost:4200")
@RequestMapping("/api/claims")
public class ClaimServiceController {
	
	@Autowired
	private ClaimService claimService;
	
	
	@PostMapping("/")
	public ResponseEntity<ClaimDetailsDto>insertClaim(@Valid @RequestBody ClaimDetailsDto claimDetailsDto)
	{
		ClaimDetailsDto insertedClaimDetailsDto=this.claimService.insertClaim(claimDetailsDto);
		return new ResponseEntity<>(insertedClaimDetailsDto,HttpStatus.CREATED);
	}
	
	@PutMapping("/{claimId}")
	public ResponseEntity<ClaimDetailsDto>updateClaim(@Valid @RequestBody ClaimDetailsDto claimDetailsDto,@PathVariable String claimId)
	{
		ClaimDetailsDto updatedClaimDetailsDto=this.claimService.updateClaim(claimDetailsDto,claimId);
		return new ResponseEntity<ClaimDetailsDto>(updatedClaimDetailsDto,HttpStatus.OK);
	}
	@GetMapping("/")
    public ResponseEntity<List< ClaimDetailsDto>> getAllClaims() {
        List<ClaimDetailsDto> claimDetailsDtos = claimService.getAllClaims();
        return ResponseEntity.ok(claimDetailsDtos);
    }
	
	@GetMapping("/{claimId}")
	public ResponseEntity<ClaimDetailsDto> getSingleClaim(@PathVariable String claimId) {
		return ResponseEntity.ok(this.claimService.getClaimDetailsById(claimId));
	}
}





