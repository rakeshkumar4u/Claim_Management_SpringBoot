
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

    @SuppressWarnings("deprecation")
	@BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        surveyorService = new SurveyorServiceImpl(surveyorRepo, claimDetailsRepo, modelMapper);
    }


    @Test
    public void testInsertSurveyor_WhenSurveyorExists() {
        // Create a SurveyorDto object for testing
        SurveyorDto surveyorDto = new SurveyorDto();
        surveyorDto.setEstimateLimt(1000);

        // Create a Surveyor object for mocking
        Surveyor existingSurveyor = new Surveyor();
        existingSurveyor.setEstimateLimt(1000);

        // Mock the behavior of surveyorRepo.findByEstimateLimt()
        Mockito.when(surveyorRepo.findByEstimateLimt(1000)).thenReturn(existingSurveyor);

        // Call the insertSurveyor() method and assert that it throws an exception
        Assertions.assertThrows(SurveyorExistsException.class, () -> surveyorService.insertSurveyor(surveyorDto));
    }

    @Test
    public void testGetAllSurveyors() {
        // Create a list of Surveyor objects for mocking
        List<Surveyor> surveyors = new ArrayList<>();
        surveyors.add(new Surveyor());
        surveyors.add(new Surveyor());

        // Mock the behavior of surveyorRepo.findAll()
        Mockito.when(surveyorRepo.findAll()).thenReturn(surveyors);

        // Mock the behavior of modelMapper.map()
        Mockito.when(modelMapper.map(Mockito.any(Surveyor.class), Mockito.eq(SurveyorDto.class)))
                .thenReturn(new SurveyorDto());

        // Call the getAllSurveyors() method
        List<SurveyorDto> result = surveyorService.getAllSurveyors();

        // Assert the result
        Assertions.assertEquals(surveyors.size(), result.size());
    }

    @Test
    public void testGetSurveyorsByEstimatedLoss_WhenClaimDetailsExist() {
        // Create a list of ClaimDetails objects for mocking
        List<ClaimDetails> claimDetailsList = new ArrayList<>();
        claimDetailsList.add(new ClaimDetails());
        claimDetailsList.add(new ClaimDetails());

        // Mock the behavior of claimDetailsRepo.findByEstimatedLoss()
        Mockito.when(claimDetailsRepo.findByEstimatedLoss(Mockito.anyInt())).thenReturn(claimDetailsList);

        // Mock the behavior of modelMapper.map()
        Mockito.when(modelMapper.map(Mockito.any(Surveyor.class), Mockito.eq(SurveyorDto.class)))
                .thenReturn(new SurveyorDto());

        // Call the getSurveyorsByEstimatedLoss() method
        List<SurveyorDto> result = surveyorService.getSurveyorsByEstimatedLoss(1000);

        // Assert the result
        Assertions.assertEquals(claimDetailsList.size(), result.size());
    }

    @Test
    public void testGetSurveyorsByEstimatedLoss_WhenClaimDetailsDoNotExist() {
        // Mock the behavior of claimDetailsRepo.findByEstimatedLoss()
        Mockito.when(claimDetailsRepo.findByEstimatedLoss(Mockito.anyInt())).thenReturn(new ArrayList<>());

        // Call the getSurveyorsByEstimatedLoss() method and assert that it throws an exception
        Assertions.assertThrows(ResourceNotFoundException.class, () -> surveyorService.getSurveyorsByEstimatedLoss(1000));
    }

    @Test
    public void testReleaseSurveyorFees_WhenClaimExists() {
        // Create a ClaimDetails object for mocking
        ClaimDetails claimDetails = new ClaimDetails();
        claimDetails.setSurveyorFees(1000);

        // Mock the behavior of claimDetailsRepo.findById()
        Mockito.when(claimDetailsRepo.findById(Mockito.anyString())).thenReturn(Optional.of(claimDetails));

        // Call the releaseSurveyorFees() method
        SurveyorFeesDto result = surveyorService.releaseSurveyorFees("claimId");

        // Assert the result
        Assertions.assertEquals("claimId", result.getClaimId());
        Assertions.assertEquals(1000, result.getSurveyorFees());
    }

    @Test
    public void testReleaseSurveyorFees_WhenClaimDoesNotExist() {
        // Mock the behavior of claimDetailsRepo.findById()
        Mockito.when(claimDetailsRepo.findById(Mockito.anyString())).thenReturn(Optional.empty());

        // Call the releaseSurveyorFees() method and assert that it throws an exception
        Assertions.assertThrows(ResourceNotFoundException.class, () -> surveyorService.releaseSurveyorFees("claimId"));
    }
}