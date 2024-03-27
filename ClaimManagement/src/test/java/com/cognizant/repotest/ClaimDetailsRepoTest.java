package com.cognizant.repotest;
import org.junit.jupiter.api.Test;
import java.util.List;
//import static org.mockito.ArgumentMatchers.*;
//import static org.mockito.Mockito.when;
import static org.junit.Assert.*;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.cognizant.entities.ClaimDetails;
import com.cognizant.repository.ClaimDetailsRepo;

import org.springframework.beans.factory.annotation.Autowired;

@DataJpaTest
public class ClaimDetailsRepoTest {

    @Autowired
    private ClaimDetailsRepo claimDetailsRepo;

    @Test
    public void findByPolicyNoAndDateOfAccidentYearTest() {
        String policyId = "12345";
        int year = 2024;
        List<ClaimDetails> result = claimDetailsRepo.findByPolicyNoAndDateOfAccidentYear(policyId, year);
        assertNotNull(result);
    }

    @Test
    public void countByClaimStatusAndDateOfAccidentYearMonthTest() {
        boolean claimStatus = true;
        int year = 2024;
        int month = 3;
        int result = claimDetailsRepo.countByClaimStatusAndDateOfAccidentYearMonth(claimStatus, year, month);
        assertTrue(result >= 0);
    }

    /*
        @Test
        public void sumAmtApprovedBySurveyorByClaimStatusAndDateOfAccidentYearMonthTest() {
            // Arrange
            boolean claimStatus = true;
            int year = 2024;
            int month = 3;
            double expectedSum = 10000.0;
            when(claimDetailsRepo.sumAmtApprovedBySurveyorByClaimStatusAndDateOfAccidentYearMonth(any(Boolean.class), any(Integer.class), any(Integer.class))).thenReturn(expectedSum);

            // Act
            double result = claimDetailsRepo.sumAmtApprovedBySurveyorByClaimStatusAndDateOfAccidentYearMonth(claimStatus, year, month);

            // Assert
            assertEquals(expectedSum, result, 0.01);
        }
        */
    }


