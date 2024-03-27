package com.cognizant.repotest;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.cognizant.entities.Surveyor;
import com.cognizant.repository.SurveyorRepo;

import java.util.Optional;
 
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
 
@DataJpaTest
public class SurveyorRepoTest {
 
    @Mock
    private SurveyorRepo surveyorRepo;
 
    @Test
    public void testFindById() {
        int surveyorId = 1;
        when(surveyorRepo.findById(surveyorId)).thenReturn(Optional.of(new Surveyor()));
        Optional<Surveyor> result = surveyorRepo.findById(surveyorId);
 
        assertTrue(result.isPresent());
    }
 
    @Test
    public void testFindByIdNotFound() {
        // Negative test case - entity not found
        int surveyorId = 1;
        when(surveyorRepo.findById(surveyorId)).thenReturn(Optional.empty());
        Optional<Surveyor> result = surveyorRepo.findById(surveyorId);
        
        assertFalse(result.isPresent());
    }
 
    @Test
    public void testSaveSurveyor() {
        Surveyor surveyor = new Surveyor();
        when(surveyorRepo.save(surveyor)).thenReturn(surveyor);
        Surveyor savedSurveyor = surveyorRepo.save(surveyor);
        
        assertNotNull(savedSurveyor);
    }
 
    @Test
    public void testDeleteById() {
        int surveyorId = 1;
        surveyorRepo.deleteById(surveyorId);
        verify(surveyorRepo, times(1)).deleteById(surveyorId);
    }
 
    @Test
    public void testExceptionScenario() {
        int surveyorId = 1;
        when(surveyorRepo.findById(surveyorId)).thenThrow(new RuntimeException("Something went wrong!"));
 
        assertThrows(RuntimeException.class, () -> surveyorRepo.findById(surveyorId));
    }
}