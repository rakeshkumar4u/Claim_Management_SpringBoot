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

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/surveyors")
@CrossOrigin(origins="http://localhost:4200")
public class SurveyorController {
 
    @Autowired
    private SurveyorService surveyorService;
 
    @GetMapping("/")
    public ResponseEntity<List< SurveyorDto>> getAllSurveyors() {
        List<SurveyorDto> surveyorDtos = surveyorService.getAllSurveyors();
        return ResponseEntity.ok(surveyorDtos);
    }
    @PostMapping("/")
	public ResponseEntity<SurveyorDto>insertSurveyor(@Valid @RequestBody SurveyorDto surveyorDto)
	{
    	SurveyorDto insertedSurveyorDto=this.surveyorService.insertSurveyor(surveyorDto);
		return new ResponseEntity<>(insertedSurveyorDto,HttpStatus.CREATED);
	}
    
    @GetMapping("/findByEstimatedLoss/{estimatedLoss}")
    public ResponseEntity<List<SurveyorDto>> findByEstimatedLoss(@PathVariable int estimatedLoss) {
        List<SurveyorDto> surveyorDtoList = surveyorService.getSurveyorsByEstimatedLoss(estimatedLoss);
        return new ResponseEntity<>(surveyorDtoList, HttpStatus.OK);
    }
    
    @PutMapping("/{claimId}")
    public ResponseEntity<SurveyorFeesDto> releaseSurveyorFees(@PathVariable String claimId) {
        // Call the releaseSurveyorFees method from the SurveyorService
        SurveyorFeesDto surveyorFeesDTO = surveyorService.releaseSurveyorFees(claimId);
        
        // Check if the surveyorFeesDTO is null or invalid
        if (surveyorFeesDTO == null) {
            return ResponseEntity.notFound().build();
        }
 
        // Return the surveyorFeesDTO in the response body with HTTP status OK
        return ResponseEntity.ok(surveyorFeesDTO);
    }
	
}







