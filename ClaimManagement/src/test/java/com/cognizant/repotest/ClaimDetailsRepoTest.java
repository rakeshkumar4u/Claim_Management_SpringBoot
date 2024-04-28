package com.cognizant.repotest;
import org.junit.jupiter.api.Test;
import java.util.List;
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
    void testGetApprovedAmountBySurveyorByInsuranceCompanyApprovalAndDateOfAccidentYearMonth_Positive() {
        // Given
        boolean insuranceCompanyApproval = true;
        int year = 2023;
        int month = 4;
        int expectedApprovedAmount = 5000; // Assuming an expected approved amount for the test case
        when(claimDetailsRepo.getApprovedAmountBySurveyorByInsuranceCompanyApprovalAndDateOfAccidentYearMonth(insuranceCompanyApproval, year, month))
            .thenReturn(expectedApprovedAmount);
 
        // When
        int approvedAmount = claimDetailsRepo.getApprovedAmountBySurveyorByInsuranceCompanyApprovalAndDateOfAccidentYearMonth(insuranceCompanyApproval, year, month);
 
        // Then
        assertEquals(expectedApprovedAmount, approvedAmount);
    }
    */
}
 


