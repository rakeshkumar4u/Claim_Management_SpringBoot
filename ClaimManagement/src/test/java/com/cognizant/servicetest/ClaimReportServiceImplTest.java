package com.cognizant.servicetest;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
 
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.cognizant.repository.ClaimDetailsRepo;
import com.cognizant.services.impl.ClaimReportServiceImpl;
 
public class ClaimReportServiceImplTest {
 
    @Mock
    private ClaimDetailsRepo claimDetailsRepo;
 
    @InjectMocks
    private ClaimReportServiceImpl claimReportService;
 
    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }
 
    // Positive test case for getPendingClaimsCount
    @Test
    public void getPendingClaimsCount_ValidData_ReturnsCount() {
        int month = 3;
        int year = 2022;
        when(claimDetailsRepo.countByClaimStatusAndDateOfAccidentYearMonth(false, year, month)).thenReturn(5);
 
        int result = claimReportService.getPendingClaimsCount(month, year);
        assertEquals(5, result);
    }
 
    // Negative test case for getPendingClaimsCount
    @Test
    public void getPendingClaimsCount_InvalidData_ReturnsZero() {
        int month = 3;
        int year = 2022;
        when(claimDetailsRepo.countByClaimStatusAndDateOfAccidentYearMonth(false, year, month)).thenReturn(0);
 
        int result = claimReportService.getPendingClaimsCount(month, year);
        assertEquals(0, result);
    }
 
    // Exception test case for getPendingClaimsCount
    @Test
    public void getPendingClaimsCount_ExceptionThrownByRepository_ThrowsException() {
        int month = 3;
        int year = 2022;
        when(claimDetailsRepo.countByClaimStatusAndDateOfAccidentYearMonth(false, year, month)).thenThrow(RuntimeException.class);
 
        assertThrows(RuntimeException.class, () -> claimReportService.getPendingClaimsCount(month, year));
    }
 
    // Positive test case for getApprovedAmountByInsuranceCompany
    @Test
    public void getApprovedAmountByInsuranceCompany_ValidData_ReturnsAmount() {
        int month = 3;
        int year = 2022;
        when(claimDetailsRepo.sumAmtApprovedBySurveyorByClaimStatusAndDateOfAccidentYearMonth(true, year, month)).thenReturn(1500.0);
 
        double result = claimReportService.getApprovedAmountByInsuranceCompany(month, year);
        assertEquals(1500.0, result, 0.01);
    }
 
    // Negative test case for getApprovedAmountByInsuranceCompany
    @Test
    public void getApprovedAmountByInsuranceCompany_InvalidData_ReturnsZero() {
        int month = 3;
        int year = 2022;
        when(claimDetailsRepo.sumAmtApprovedBySurveyorByClaimStatusAndDateOfAccidentYearMonth(true, year, month)).thenReturn(0.0);
 
        double result = claimReportService.getApprovedAmountByInsuranceCompany(month, year);
        assertEquals(0.0, result, 0.01);
    }
 
    // Exception test case for getApprovedAmountByInsuranceCompany
    @Test
    public void getApprovedAmountByInsuranceCompany_ExceptionThrownByRepository_ThrowsException() {
        int month = 3;
        int year = 2022;
        when(claimDetailsRepo.sumAmtApprovedBySurveyorByClaimStatusAndDateOfAccidentYearMonth(true, year, month)).thenThrow(RuntimeException.class);
 
        assertThrows(RuntimeException.class, () -> claimReportService.getApprovedAmountByInsuranceCompany(month, year));
    }
 
 
}
