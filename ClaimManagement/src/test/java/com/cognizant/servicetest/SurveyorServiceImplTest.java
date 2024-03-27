
package com.cognizant.servicetest;

import static org.junit.jupiter.api.Assertions.*;

import static org.mockito.Mockito.*;

import java.util.Arrays;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;

import org.junit.jupiter.api.Test;

import org.mockito.InjectMocks;

import org.mockito.Mock;

import org.mockito.MockitoAnnotations;

import org.modelmapper.ModelMapper;
 
import com.cognizant.dto.SurveyorDto;

import com.cognizant.entities.Surveyor;

import com.cognizant.repository.SurveyorRepo;

import com.cognizant.services.impl.SurveyorServiceImpl;

public class SurveyorServiceImplTest {
    @Mock
    private SurveyorRepo surveyorRepo;
    @Mock
    private ModelMapper modelMapper;
    @InjectMocks
    private SurveyorServiceImpl surveyorService;
    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void getAllSurveyors_PositiveCase() {
        Surveyor surveyor1 = new Surveyor(0, null, null, 0, null);
        Surveyor surveyor2 = new Surveyor(0, null, null, 0, null);
        List<Surveyor> surveyorList = Arrays.asList(surveyor1, surveyor2);
        when(surveyorRepo.findAll()).thenReturn(surveyorList);
        SurveyorDto surveyorDto1 = new SurveyorDto(0, "Rakesh", "Kumar", 5000);
        SurveyorDto surveyorDto2 = new SurveyorDto(0, "Manish", "Kumar", 7000);
        List<SurveyorDto> expectedSurveyorDtos = Arrays.asList(surveyorDto1, surveyorDto2);
        when(modelMapper.map(any(), eq(SurveyorDto.class))).thenReturn(surveyorDto1, surveyorDto2);

        List<SurveyorDto> result = surveyorService.getAllSurveyors();
        assertEquals(expectedSurveyorDtos, result);

        verify(surveyorRepo).findAll();
        verify(modelMapper, times(2)).map(any(), eq(SurveyorDto.class));
    }

    @Test
    public void getAllSurveyors_EmptyList() {
        when(surveyorRepo.findAll()).thenReturn(Arrays.asList());
        List<SurveyorDto> result = surveyorService.getAllSurveyors();
        
        assertTrue(result.isEmpty());
        verify(surveyorRepo).findAll();
        verifyNoInteractions(modelMapper);
    }

    @Test
    public void getAllSurveyors_ExceptionCase() {
        when(surveyorRepo.findAll()).thenThrow(RuntimeException.class);
        assertThrows(RuntimeException.class, () -> surveyorService.getAllSurveyors());
        verify(surveyorRepo).findAll();
        verifyNoInteractions(modelMapper);
    }
}
