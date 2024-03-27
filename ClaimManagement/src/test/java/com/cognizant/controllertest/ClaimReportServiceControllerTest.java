package com.cognizant.controllertest;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
 
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.cognizant.controller.ClaimReportServiceController;
import com.cognizant.services.ClaimReportService;
 
public class ClaimReportServiceControllerTest {
 
    @Mock
    private ClaimReportService claimReportService;
 
    @InjectMocks
    private ClaimReportServiceController claimReportServiceController;
 
    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }
 
    @Test
    public void getPendingClaimsCount_ValidData_ReturnsCount() {
        int month = 3;
        int year = 2022;
        when(claimReportService.getPendingClaimsCount(eq(month), eq(year))).thenReturn(5);
 
        ResponseEntity<Integer> responseEntity = claimReportServiceController.getPendingClaimsCount(month, year);
 
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertNotNull(responseEntity.getBody());
        assertEquals(5, responseEntity.getBody());
        
    }
 
    @Test
    public void getPendingClaimsCount_InvalidData_ReturnsBadRequest() {
        int month = 3;
        int year = 2022;
        when(claimReportService.getPendingClaimsCount(eq(month), eq(year))).thenReturn(0);
 
        ResponseEntity<Integer> responseEntity = claimReportServiceController.getPendingClaimsCount(month, year);
 
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode()); 
        assertNotNull(responseEntity.getBody());
        assertEquals(0, responseEntity.getBody());
        
    }
 
    // Exception test case for getPendingClaimsCount
    @Test
    public void getPendingClaimsCount_ExceptionThrownByService_ReturnsInternalServerError() {
        int month = 3;
        int year = 2022;
        when(claimReportService.getPendingClaimsCount(eq(month), eq(year))).thenThrow(RuntimeException.class);
 
        ResponseEntity<Integer> responseEntity = claimReportServiceController.getPendingClaimsCount(month, year);
 
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, responseEntity.getStatusCode());
        assertNull(responseEntity.getBody());
    }
 
    // Positive test case for getApprovedAmountByInsuranceCompany
    @Test
    public void getApprovedAmountByInsuranceCompany_ValidData_ReturnsAmount() {
        int month = 3;
        int year = 2022;
        when(claimReportService.getApprovedAmountByInsuranceCompany(eq(month), eq(year))).thenReturn(1500.0);
 
        ResponseEntity<Double> responseEntity = claimReportServiceController.getApprovedAmountByInsuranceCompany(month, year);
 
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertNotNull(responseEntity.getBody());
        assertEquals(1500.0, responseEntity.getBody(), 0.01); 
 
    }
 
    // Negative test case for getApprovedAmountByInsuranceCompany
    @Test
    public void getApprovedAmountByInsuranceCompany_InvalidData_ReturnsBadRequest() {
        int month = 3;
        int year = 2022;
        when(claimReportService.getApprovedAmountByInsuranceCompany(eq(month), eq(year))).thenReturn(0.0);
 
        ResponseEntity<Double> responseEntity = claimReportServiceController.getApprovedAmountByInsuranceCompany(month, year);
 
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertNotNull(responseEntity.getBody());
        assertEquals(0.0, responseEntity.getBody(), 0.01); 
        
    }
 
    // Exception test case for getApprovedAmountByInsuranceCompany
    @Test
    public void getApprovedAmountByInsuranceCompany_ExceptionThrownByService_ReturnsInternalServerError() {
        int month = 3;
        int year = 2022;
        when(claimReportService.getApprovedAmountByInsuranceCompany(eq(month), eq(year))).thenThrow(RuntimeException.class);
 
        ResponseEntity<Double> responseEntity = claimReportServiceController.getApprovedAmountByInsuranceCompany(month, year);
 
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, responseEntity.getStatusCode());
        assertNull(responseEntity.getBody());
    }  
}
