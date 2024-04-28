package com.cognizant.controllertest;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import java.util.ArrayList;
import java.util.List;
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

    @InjectMocks
    private ClaimServiceController claimServiceController;

    @Mock
    private ClaimService claimService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testInsertClaim_Positive() {
        ClaimDetailsDto claimDetailsDto = new ClaimDetailsDto();
        when(claimService.insertClaim(any(ClaimDetailsDto.class))).thenReturn(claimDetailsDto);

        ResponseEntity<ClaimDetailsDto> response = claimServiceController.insertClaim(claimDetailsDto);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(claimDetailsDto, response.getBody());
    }

    @Test
    public void testUpdateClaim_Positive() {
        ClaimDetailsDto claimDetailsDto = new ClaimDetailsDto();
        String claimId = "CLAIM123";
        when(claimService.updateClaim(any(ClaimDetailsDto.class), eq(claimId))).thenReturn(claimDetailsDto);

        ResponseEntity<ClaimDetailsDto> response = claimServiceController.updateClaim(claimDetailsDto, claimId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(claimDetailsDto, response.getBody());
    }

    @Test
    public void testGetAllClaims_Positive() {
        List<ClaimDetailsDto> claimDetailsDtos = new ArrayList<>();
        claimDetailsDtos.add(new ClaimDetailsDto());
        when(claimService.getAllClaims()).thenReturn(claimDetailsDtos);

        ResponseEntity<List<ClaimDetailsDto>> response = claimServiceController.getAllClaims();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(claimDetailsDtos, response.getBody());
    }

    @Test
    public void testGetSingleClaim_Positive() {
        String claimId = "CLAIM123";
        ClaimDetailsDto claimDetailsDto = new ClaimDetailsDto();
        when(claimService.getClaimDetailsById(claimId)).thenReturn(claimDetailsDto);

        ResponseEntity<ClaimDetailsDto> response = claimServiceController.getSingleClaim(claimId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(claimDetailsDto, response.getBody());
    }
}