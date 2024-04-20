package com.cognizant.services.impl;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cognizant.dto.SurveyorDto;
import com.cognizant.dto.SurveyorFeesDto;
import com.cognizant.entities.ClaimDetails;
import com.cognizant.entities.Surveyor;
import com.cognizant.exception.ResourceNotFoundException;
import com.cognizant.exception.SurveyorExistsException;
import com.cognizant.repository.ClaimDetailsRepo;
import com.cognizant.repository.SurveyorRepo;
import com.cognizant.services.SurveyorService;

import jakarta.transaction.Transactional;

@Service
public class SurveyorServiceImpl implements SurveyorService {
	
	private final SurveyorRepo surveyorRepo;
	private final ClaimDetailsRepo claimDetailsRepo;
	private final ModelMapper modelMapper;
	
	@Autowired
	public SurveyorServiceImpl(SurveyorRepo surveyorRepo,ClaimDetailsRepo claimDetailsRepo, ModelMapper modelMapper)
	{
		this.surveyorRepo=surveyorRepo;
		this.modelMapper=modelMapper;
		this.claimDetailsRepo=claimDetailsRepo;
	}
	
	
	 @Override
	    @Transactional
	    public SurveyorDto insertSurveyor(SurveyorDto surveyorDto) {
	        // Check if a surveyor with the same estimateLimt already exists
	        Surveyor existingSurveyor = surveyorRepo.findByEstimateLimt(surveyorDto.getEstimateLimt());
	        if (existingSurveyor != null) {
	            throw new SurveyorExistsException("Surveyor already exists with estimateLimt: " + surveyorDto.getEstimateLimt());
	        }
	 
	       Surveyor surveyor = modelMapper.map(surveyorDto, Surveyor.class);
	       Surveyor addedSurveyor = surveyorRepo.save(surveyor);
	 
	      return modelMapper.map(addedSurveyor, SurveyorDto.class);
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
	
	@Override
    public List<SurveyorDto> getSurveyorsByEstimatedLoss(int estimatedLoss) {
        List<ClaimDetails> claimDetailsList = claimDetailsRepo.findByEstimatedLoss(estimatedLoss);
        List<SurveyorDto> surveyorDtoList = new ArrayList<>();
        
        if(claimDetailsList.isEmpty()) {
        	throw new ResourceNotFoundException("No Surveyor Found for estimated loss: "+estimatedLoss);
        }
           
        for (ClaimDetails claimDetails : claimDetailsList) {
       surveyorDtoList.add(modelMapper.map(claimDetails.getSurveyor(), SurveyorDto.class));
        }
 
        return surveyorDtoList;
    }
	
	@Override
	@Transactional
	public SurveyorFeesDto releaseSurveyorFees(String claimId) {
	    // Retrieve existing claim details
	    ClaimDetails existingClaim = claimDetailsRepo.findById(claimId)
	            .orElseThrow(() -> new ResourceNotFoundException("Claim not found with ID " + claimId));
	 
	    // Get the surveyor fee from the existing claim
	    int surveyorFee = existingClaim.getSurveyorFees();
	 
	    // Create and return SurveyorFeesDTO
	    SurveyorFeesDto surveyorFeesDto = new SurveyorFeesDto();
	    surveyorFeesDto.setClaimId(claimId);
	    surveyorFeesDto.setSurveyorFees(surveyorFee);
	    return surveyorFeesDto;
	}
}
