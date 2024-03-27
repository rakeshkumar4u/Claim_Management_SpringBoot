package com.cognizant.controllertest;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.cognizant.controller.SurveyorController;
import com.cognizant.dto.SurveyorDto;
import com.cognizant.services.SurveyorService;

import java.util.Arrays;
import java.util.List;
 
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
 
@ExtendWith(MockitoExtension.class)
public class SurveyorControllerTest {
 
    @Mock
    private SurveyorService surveyorService;
 
    @InjectMocks
    private SurveyorController surveyorController;
 
    @Test
    public void testGetAllSurveyors_Positive() {
        List<SurveyorDto> surveyorDTOList = Arrays.asList(
                new SurveyorDto(0, "John", "Doe", 1000),
                new SurveyorDto(0, "Jane", "Smith", 1500)
        );
 
        when(surveyorService.getAllSurveyors()).thenReturn(surveyorDTOList);
 
        ResponseEntity<List<SurveyorDto>> response = surveyorController.getAllSurveyors();
 
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(2, response.getBody().size());
        assertEquals("John", response.getBody().get(0).getFirstName());
        assertEquals("Jane", response.getBody().get(1).getFirstName());
    }
 
    @Test
    public void testGetAllSurveyors_EmptyList() {
        when(surveyorService.getAllSurveyors()).thenReturn(Arrays.asList());
        ResponseEntity<List<SurveyorDto>> response = surveyorController.getAllSurveyors();
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertTrue(response.getBody().isEmpty());
    }
 
    @Test
    public void testGetAllSurveyors_Exception() {
        when(surveyorService.getAllSurveyors()).thenThrow(RuntimeException.class);
        assertThrows(RuntimeException.class, () -> surveyorController.getAllSurveyors());
    }
}


