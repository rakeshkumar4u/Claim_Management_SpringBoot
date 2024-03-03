package com.cognizant.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cognizant.dto.SurveyorDto;
import com.cognizant.entities.Surveyor;
import com.cognizant.repository.SurveyorRepo;
import com.cognizant.services.SurveyorService;

@Service
public class ServeyorServiceImpl implements SurveyorService {
	@Autowired
	private SurveyorRepo surveyorRepo;
	
	@Autowired
	private ModelMapper modelMapper;

	@Override
	public List<SurveyorDto> getAllSurveyors() {
		List<Surveyor> surveyors=this.surveyorRepo.findAll();
		
		List<SurveyorDto> surveyorDtos=
				surveyors.stream().map((surveyor)->this.modelMapper.map(surveyor,SurveyorDto.class))
				.collect(Collectors.toList());
				return surveyorDtos;
	}

}
