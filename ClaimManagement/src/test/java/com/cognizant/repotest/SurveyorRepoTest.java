package com.cognizant.repotest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import com.cognizant.entities.Surveyor;
import com.cognizant.repository.SurveyorRepo;
import static org.junit.jupiter.api.Assertions.*;
 
@DataJpaTest
public class SurveyorRepoTest {
 
    @Autowired
    private SurveyorRepo surveyorRepo;
 
    @Test
    public void testFindByEstimateLimit_Positive() {

        int estimateLimit = 1000;
        Surveyor surveyor = new Surveyor();
        surveyor.setEstimateLimt(estimateLimit);
        surveyorRepo.save(surveyor);
 
        Surveyor actualSurveyor = surveyorRepo.findByEstimateLimt(estimateLimit);
        assertNotNull(actualSurveyor);
        assertEquals(estimateLimit, actualSurveyor.getEstimateLimt());

    }
 
    @Test
    public void testFindByEstimateLimit_Negative() {
        int estimateLimit = 1000;
        Surveyor actualSurveyor = surveyorRepo.findByEstimateLimt(estimateLimit);
 
        assertNull(actualSurveyor);
    }

}
