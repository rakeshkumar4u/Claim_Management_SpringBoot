package com.cognizant.dto;

import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SurveyorDto {


	private int surveyorId;
	
	private String firstName;
	
	private String lastName;
    
	@Min(value=0,message="The value cannot be negative")
	private int estimateLimt;

}


