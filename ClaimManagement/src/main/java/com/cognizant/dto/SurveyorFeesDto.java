package com.cognizant.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SurveyorFeesDto {
	private String claimId;
	private int surveyorFees;

}
