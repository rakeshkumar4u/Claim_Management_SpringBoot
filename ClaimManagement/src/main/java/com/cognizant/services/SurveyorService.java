package com.cognizant.services;

import java.util.List;

import com.cognizant.dto.SurveyorDto;

public interface SurveyorService {

	public List<SurveyorDto> getAllSurveyors();
	SurveyorDto insertSurveyor(SurveyorDto surveyorDto);

}
