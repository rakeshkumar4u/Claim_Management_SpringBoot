package com.cognizant.repotest;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import com.cognizant.entities.Surveyor;
import com.cognizant.repository.SurveyorRepo; 
@ExtendWith(MockitoExtension.class)

@DataJpaTest
class SurveyorRepoTest {

	@Mock
	private SurveyorRepo surveyorRepo;

	@BeforeEach
	void setUp() {
	}

	@Test
	void testFindByEstimateLimt_Positive() {
		int estimateLimit = 10000;
		Surveyor mockSurveyor = new Surveyor();
		mockSurveyor.setEstimateLimt(estimateLimit);
		when(surveyorRepo.findByEstimateLimt(estimateLimit)).thenReturn(mockSurveyor);
		Surveyor foundSurveyor = surveyorRepo.findByEstimateLimt(estimateLimit);

		// Then
		assertNotNull(foundSurveyor);
		assertEquals(estimateLimit, foundSurveyor.getEstimateLimt());
	}

	@Test
	void testFindByEstimateLimt_Negative() {
		int estimateLimit = 10000;
		when(surveyorRepo.findByEstimateLimt(estimateLimit)).thenReturn(null);
		Surveyor foundSurveyor = surveyorRepo.findByEstimateLimt(estimateLimit);
		
		// Then
		assertNull(foundSurveyor);

	}
	
	
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
