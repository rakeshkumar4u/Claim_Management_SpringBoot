package com.cognizant.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cognizant.dto.SurveyorDto;
import java.util.*;

import com.cognizant.services.SurveyorService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/surveyors")
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
	
}






