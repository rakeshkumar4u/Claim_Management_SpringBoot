package com.cognizant.controller;

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

import com.cognizant.dto.SurveyorDto;
import com.cognizant.dto.SurveyorFeesDto;

import java.util.*;

import com.cognizant.services.SurveyorService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

/**
 * @author Rakesh Kumar
 * This class represents REST API endpoints for Surveyor
 */

@RestController
@RequestMapping("/api/surveyors")
@CrossOrigin(origins="http://localhost:4200")
@Tag(name="Surveyor Controller", description="Surveyor Controller REST API")
public class SurveyorController {
 
    @Autowired
    private SurveyorService surveyorService;
 
    @GetMapping("/")
    @Operation(description="Retrieve All Resource of Type Surveyor")
    public ResponseEntity<List< SurveyorDto>> getAllSurveyors() {
        List<SurveyorDto> surveyorDtos = surveyorService.getAllSurveyors();
        return ResponseEntity.ok(surveyorDtos);
    }
    @PostMapping("/")
    @Operation(description="Add New Surveyor")
	public ResponseEntity<SurveyorDto>insertSurveyor(@Valid @RequestBody SurveyorDto surveyorDto)
	{
    	SurveyorDto insertedSurveyorDto=this.surveyorService.insertSurveyor(surveyorDto);
		return new ResponseEntity<>(insertedSurveyorDto,HttpStatus.CREATED);
	}
    
    @GetMapping("/{estimatedLoss}")
    @Operation(description = "Find Surveyors by Estimated Loss")
    public ResponseEntity<List<SurveyorDto>> findByEstimatedLoss(@PathVariable int estimatedLoss) {
        List<SurveyorDto> surveyorDtoList = surveyorService.getSurveyorsByEstimatedLoss(estimatedLoss);
        return new ResponseEntity<>(surveyorDtoList, HttpStatus.OK);
    }
    
    @PutMapping("/{claimId}")
    @Operation(description = "Update SurveyorFeesDto and Release Surveyor Fees" )
    public ResponseEntity<SurveyorFeesDto> releaseSurveyorFees(@PathVariable String claimId) {
        SurveyorFeesDto surveyorFeesDto = surveyorService.releaseSurveyorFees(claimId); 
        // Check if the surveyorFeesDTO is null or invalid
        if (surveyorFeesDto== null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(surveyorFeesDto);
    }	
}







