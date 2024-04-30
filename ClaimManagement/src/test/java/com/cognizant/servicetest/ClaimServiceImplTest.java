package com.cognizant.servicetest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;

import com.cognizant.dto.ClaimDetailsDto;
import com.cognizant.dto.PolicyDto;
import com.cognizant.dto.SurveyorDto;
import com.cognizant.entities.ClaimDetails;
import com.cognizant.exception.InvalidPolicyException;
import com.cognizant.exception.ResourceNotFoundException;
import com.cognizant.repository.ClaimDetailsRepo;
import com.cognizant.repository.PolicyRepo;
import com.cognizant.repository.SurveyorRepo;
import com.cognizant.services.impl.ClaimServiceImpl;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ClaimServiceImplTest {

    @Mock
    private PolicyRepo policyRepo;

    @Mock
    private ClaimDetailsRepo claimDetailsRepo;

    @Mock
    private SurveyorRepo surveyorRepo;

    @Mock
    private ModelMapper modelMapper;

    private ClaimServiceImpl claimService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        claimService = new ClaimServiceImpl(policyRepo, claimDetailsRepo, surveyorRepo, modelMapper);
    }

    @Test
    public void testInsertClaim_WhenPolicyDoesNotExist() {
        ClaimDetailsDto claimDetailsDto = new ClaimDetailsDto();
        claimDetailsDto.setPolicy(new PolicyDto());
        claimDetailsDto.setSurveyor(new SurveyorDto());
        claimDetailsDto.setDateOfAccident(LocalDate.now());
        claimDetailsDto.setEstimatedLoss(5000);

        // Mock the behavior of policyRepo.findById()
        Mockito.when(policyRepo.findById(Mockito.anyString())).thenReturn(Optional.empty());
        Assertions.assertThrows(InvalidPolicyException.class, () -> claimService.insertClaim(claimDetailsDto));
    }

    @Test
    public void testUpdateClaim_WhenClaimDoesNotExist() {
        ClaimDetailsDto claimDetailsDto = new ClaimDetailsDto();
        claimDetailsDto.setClaimStatus(true);
        claimDetailsDto.setAmtApprovedBySurveyor(1000);
        claimDetailsDto.setSurveyor(new SurveyorDto());
        claimDetailsDto.getSurveyor().setSurveyorId(1);

        // Mock the behavior of claimDetailsRepo.findById()
        Mockito.when(claimDetailsRepo.findById(Mockito.anyString())).thenReturn(Optional.empty());
        Assertions.assertThrows(ResourceNotFoundException.class, () -> claimService.updateClaim(claimDetailsDto, "CLAIM123"));
    }

    @Test
    public void testGetAllClaims() {
        List<ClaimDetails> claims = new ArrayList<>();
        claims.add(new ClaimDetails());
        claims.add(new ClaimDetails());
        Mockito.when(claimDetailsRepo.findAll()).thenReturn(claims);

        // Mock the behavior of modelMapper.map()
        Mockito.when(modelMapper.map(Mockito.any(ClaimDetails.class), Mockito.eq(ClaimDetailsDto.class)))
                .thenReturn(new ClaimDetailsDto());
        List<ClaimDetailsDto> result = claimService.getAllClaims();

        Assertions.assertEquals(claims.size(), result.size());
    }

  

    @Test
    public void testGetClaimDetailsById_WhenClaimDoesNotExist() {
        // Mock the behavior of claimDetailsRepo.findById()
        Mockito.when(claimDetailsRepo.findById(Mockito.anyString())).thenReturn(Optional.empty());
        Assertions.assertThrows(ResourceNotFoundException.class, () -> claimService.getClaimDetailsById("CLAIM123"));
    }
}