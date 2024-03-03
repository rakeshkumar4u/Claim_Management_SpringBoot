package com.cognizant.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.PositiveOrZero;

import lombok.Data;

@Data
public class SurveyorDto {
	
	@NotEmpty
	private Integer Surveyorld;
	
	private String FirstName;
	
	private String LastName;
    
	@PositiveOrZero
	private Integer EstimateLimt;

}
