package com.cognizant.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SurveyorDto {


	private int surveyorId;
	
	@NotBlank(message="First Name is mandatory")
	private String firstName;
	
	@NotBlank(message="Last Name is mandatory")
	private String lastName;
    
	@Min(value=0,message="Estimate Limit cannot be negative")
	private int estimateLimt;

}



