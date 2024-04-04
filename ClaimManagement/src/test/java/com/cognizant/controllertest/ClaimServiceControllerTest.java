package com.cognizant.controllertest;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.cognizant.controller.ClaimServiceController;
import com.cognizant.dto.ClaimDetailsDto;
import com.cognizant.services.ClaimService;
 
public class ClaimServiceControllerTest {
 
    @Mock
    private ClaimService claimService;
 
    @InjectMocks
    private ClaimServiceController claimServiceController;
 
    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }
 
    @Test
    public void insertClaim_ValidClaimDetails_ReturnsCreated() {
        ClaimDetailsDto claimDetailsDto = new ClaimDetailsDto();
        when(claimService.insertClaim(eq(claimDetailsDto))).thenReturn(claimDetailsDto);
 
        ResponseEntity<ClaimDetailsDto> responseEntity = claimServiceController.insertClaim(claimDetailsDto);
 
        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        assertNotNull(responseEntity.getBody());
       
    }
 
    @Test
    public void insertClaim_InvalidClaimDetails_ReturnsBadRequest() {
        
        ClaimDetailsDto claimDetailsDto = new ClaimDetailsDto("CL456", null, 0, null, false, null, 0, false, false, 0);
 
        ResponseEntity<ClaimDetailsDto> responseEntity = claimServiceController.insertClaim(claimDetailsDto);
 
        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
        assertNull(responseEntity.getBody());
    }
 
    @Test
    public void insertClaim_ExceptionThrownByService_ReturnsInternalServerError() {
        ClaimDetailsDto claimDetailsDto = new ClaimDetailsDto();
        when(claimService.insertClaim(eq(claimDetailsDto))).thenThrow(RuntimeException.class);
 
        ResponseEntity<ClaimDetailsDto> responseEntity = claimServiceController.insertClaim(claimDetailsDto);
 
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, responseEntity.getStatusCode());
        assertNull(responseEntity.getBody());
    }
 
    @Test
    public void updateClaim_ValidClaimDetails_ReturnsOk() {
        
        String claimId = "CL123";
        ClaimDetailsDto claimDetailsDto = new ClaimDetailsDto();
        when(claimService.updateClaim(eq(claimDetailsDto), eq(claimId))).thenReturn(claimDetailsDto);
 
        ResponseEntity<ClaimDetailsDto> responseEntity = claimServiceController.updateClaim(claimDetailsDto, claimId);
         assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
         assertNotNull(responseEntity.getBody());
    }
 
    // Negative test case for updateClaim
    @Test
    public void updateClaim_InvalidClaimDetails_ReturnsBadRequest() {
        
        String claimId = "CL456";
        ClaimDetailsDto claimDetailsDto = new ClaimDetailsDto();
 
        ResponseEntity<ClaimDetailsDto> responseEntity = claimServiceController.updateClaim(claimDetailsDto, claimId);
 
        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
        assertNull(responseEntity.getBody());
    }
 
    // Exception test case for updateClaim
    @Test
    public void updateClaim_ExceptionThrownByService_ReturnsInternalServerError() {
       
        String claimId = "CL789";
        ClaimDetailsDto claimDetailsDto = new ClaimDetailsDto();
        when(claimService.updateClaim(eq(claimDetailsDto), eq(claimId))).thenThrow(RuntimeException.class);
 
        ResponseEntity<ClaimDetailsDto> responseEntity = claimServiceController.updateClaim(claimDetailsDto, claimId);
 
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, responseEntity.getStatusCode());
        assertNull(responseEntity.getBody());
    }
 
 
}