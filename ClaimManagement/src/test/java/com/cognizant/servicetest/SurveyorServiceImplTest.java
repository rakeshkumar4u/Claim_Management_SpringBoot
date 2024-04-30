
package com.cognizant.servicetest;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
 
import com.cognizant.dto.SurveyorDto;
import com.cognizant.dto.SurveyorFeesDto;
import com.cognizant.entities.ClaimDetails;
import com.cognizant.entities.Surveyor;
import com.cognizant.exception.ResourceNotFoundException;
import com.cognizant.exception.SurveyorExistsException;
import com.cognizant.repository.ClaimDetailsRepo;
import com.cognizant.repository.SurveyorRepo;
import com.cognizant.services.impl.SurveyorServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.mockito.Mockito;
import java.util.ArrayList;
import java.util.Optional;

public class SurveyorServiceImplTest {

    @Mock
    private SurveyorRepo surveyorRepo;

    @Mock
    private ClaimDetailsRepo claimDetailsRepo;

    @Mock
    private ModelMapper modelMapper;

    private SurveyorServiceImpl surveyorService;

	@BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        surveyorService = new SurveyorServiceImpl(surveyorRepo, claimDetailsRepo, modelMapper);
    }


    @Test
    public void testInsertSurveyor_WhenSurveyorExists() {
        SurveyorDto surveyorDto = new SurveyorDto();
        surveyorDto.setEstimateLimt(1000);

        Surveyor existingSurveyor = new Surveyor();
        existingSurveyor.setEstimateLimt(1000);

        Mockito.when(surveyorRepo.findByEstimateLimt(1000)).thenReturn(existingSurveyor);
        Assertions.assertThrows(SurveyorExistsException.class, () -> surveyorService.insertSurveyor(surveyorDto));
    }

    @Test
    public void testGetAllSurveyors() {
        List<Surveyor> surveyors = new ArrayList<>();
        surveyors.add(new Surveyor());
        surveyors.add(new Surveyor());
        Mockito.when(surveyorRepo.findAll()).thenReturn(surveyors);

        // Mock the behavior of modelMapper.map()
        Mockito.when(modelMapper.map(Mockito.any(Surveyor.class), Mockito.eq(SurveyorDto.class)))
                .thenReturn(new SurveyorDto());
        List<SurveyorDto> result = surveyorService.getAllSurveyors();

        Assertions.assertEquals(surveyors.size(), result.size());
    }

    @Test
    public void testGetSurveyorsByEstimatedLoss_WhenClaimDetailsExist() {
        List<ClaimDetails> claimDetailsList = new ArrayList<>();
        claimDetailsList.add(new ClaimDetails());
        claimDetailsList.add(new ClaimDetails());

        Mockito.when(claimDetailsRepo.findByEstimatedLoss(Mockito.anyInt())).thenReturn(claimDetailsList);

        Mockito.when(modelMapper.map(Mockito.any(Surveyor.class), Mockito.eq(SurveyorDto.class)))
                .thenReturn(new SurveyorDto());
        List<SurveyorDto> result = surveyorService.getSurveyorsByEstimatedLoss(1000);

        Assertions.assertEquals(claimDetailsList.size(), result.size());
    }

    @Test
    public void testGetSurveyorsByEstimatedLoss_WhenClaimDetailsDoNotExist() {
        Mockito.when(claimDetailsRepo.findByEstimatedLoss(Mockito.anyInt())).thenReturn(new ArrayList<>());
        Assertions.assertThrows(ResourceNotFoundException.class, () -> surveyorService.getSurveyorsByEstimatedLoss(1000));
    }

    @Test
    public void testReleaseSurveyorFees_WhenClaimExists() {
        ClaimDetails claimDetails = new ClaimDetails();
        claimDetails.setSurveyorFees(1000);
        Mockito.when(claimDetailsRepo.findById(Mockito.anyString())).thenReturn(Optional.of(claimDetails));

        SurveyorFeesDto result = surveyorService.releaseSurveyorFees("claimId");
        
        Assertions.assertEquals("claimId", result.getClaimId());
        Assertions.assertEquals(1000, result.getSurveyorFees());
    }

    @Test
    public void testReleaseSurveyorFees_WhenClaimDoesNotExist() {
        Mockito.when(claimDetailsRepo.findById(Mockito.anyString())).thenReturn(Optional.empty());
        Assertions.assertThrows(ResourceNotFoundException.class, () -> surveyorService.releaseSurveyorFees("claimId"));
    }
}