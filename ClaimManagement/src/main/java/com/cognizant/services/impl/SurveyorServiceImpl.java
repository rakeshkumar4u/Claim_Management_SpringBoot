package com.cognizant.services.impl;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cognizant.dto.SurveyorDto;
import com.cognizant.entities.Surveyor;
import com.cognizant.repository.SurveyorRepo;
import com.cognizant.services.SurveyorService;

import jakarta.transaction.Transactional;

@Service
public class SurveyorServiceImpl implements SurveyorService {
	
	private final SurveyorRepo surveyorRepo;
	private final ModelMapper modelMapper;
	
	@Autowired
	public SurveyorServiceImpl(SurveyorRepo surveyorRepo,ModelMapper modelMapper)
	{
		this.surveyorRepo=surveyorRepo;
		this.modelMapper=modelMapper;
	}
	
	
	@Override
	@Transactional
	public SurveyorDto insertSurveyor(SurveyorDto surveyorDto) {
		Surveyor surv=this.modelMapper.map(surveyorDto,Surveyor.class);
		Surveyor addedSurv=this.surveyorRepo.save(surv); 
		return this.modelMapper.map(addedSurv,SurveyorDto.class);
	}

	@Override
	@Transactional
	public List<SurveyorDto> getAllSurveyors() {
		List<Surveyor> surveyors=this.surveyorRepo.findAll();
		 List<SurveyorDto> surveyorDtos = new ArrayList<>();
	        for (Surveyor surveyor : surveyors) {
	            surveyorDtos.add(modelMapper.map(surveyor, SurveyorDto.class));
	        }
	        return surveyorDtos;
	}	
}

