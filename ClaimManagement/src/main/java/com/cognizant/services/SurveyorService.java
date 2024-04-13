package com.cognizant.services;

import java.util.List;

import com.cognizant.dto.SurveyorDto;
import com.cognizant.dto.SurveyorFeesDto;

public interface SurveyorService {

	public List<SurveyorDto> getAllSurveyors();
	SurveyorDto insertSurveyor(SurveyorDto surveyorDto);
	List<SurveyorDto> getSurveyorsByEstimatedLoss(int estimatedLoss);
	SurveyorFeesDto releaseSurveyorFees(String claimId);

}
