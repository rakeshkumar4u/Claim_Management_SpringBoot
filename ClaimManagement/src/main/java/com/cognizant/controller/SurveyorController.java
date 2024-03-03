package com.cognizant.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cognizant.dto.SurveyorDto;
import com.cognizant.services.SurveyorService;

@RestController
@RequestMapping("/api/surveyors")
public class SurveyorController {
	
	@Autowired
	private SurveyorService surveyorService;
	
	@GetMapping("/")
	public ResponseEntity<List<SurveyorDto>> getAllSurveyors()
	{
		return ResponseEntity.ok(this.surveyorService.getAllSurveyors());
		
	}
	

}
