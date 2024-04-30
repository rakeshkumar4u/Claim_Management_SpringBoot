package com.cognizant.controllertest;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.cognizant.controller.SurveyorController;
import com.cognizant.dto.SurveyorDto;
import com.cognizant.dto.SurveyorFeesDto;
import com.cognizant.services.SurveyorService;

public class SurveyorControllerTest {

    @InjectMocks
    private SurveyorController surveyorController;

    @Mock
    private SurveyorService surveyorService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetAllSurveyors() {
        List<SurveyorDto> surveyorDtos = new ArrayList<>();
        surveyorDtos.add(new SurveyorDto(1, "Rakesh", "Kumar", 6000));
        when(surveyorService.getAllSurveyors()).thenReturn(surveyorDtos);

        ResponseEntity<List<SurveyorDto>> response = surveyorController.getAllSurveyors();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(surveyorDtos, response.getBody());
    }

    @Test
    public void testInsertSurveyor() {
        SurveyorDto surveyorDto = new SurveyorDto(1, "Rakesh", "Kumar", 6000);
        when(surveyorService.insertSurveyor(any(SurveyorDto.class))).thenReturn(surveyorDto);

        ResponseEntity<SurveyorDto> response = surveyorController.insertSurveyor(surveyorDto);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(surveyorDto, response.getBody());
    }

    @Test
    public void testFindByEstimatedLoss() {
        int estimatedLoss = 5000;
        List<SurveyorDto> surveyorDtoList = new ArrayList<>();
        surveyorDtoList.add(new SurveyorDto(1, "Rakesh", "Kumar", 6000));
        when(surveyorService.getSurveyorsByEstimatedLoss(estimatedLoss)).thenReturn(surveyorDtoList);

        ResponseEntity<List<SurveyorDto>> response = surveyorController.findByEstimatedLoss(estimatedLoss);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(surveyorDtoList, response.getBody());
    }

    @Test
    public void testReleaseSurveyorFees() {
        String claimId = "CLAIM123";
        SurveyorFeesDto surveyorFeesDto = new SurveyorFeesDto(claimId, 1000);
        when(surveyorService.releaseSurveyorFees(claimId)).thenReturn(surveyorFeesDto);

        ResponseEntity<SurveyorFeesDto> response = surveyorController.releaseSurveyorFees(claimId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(surveyorFeesDto, response.getBody());
    }
}