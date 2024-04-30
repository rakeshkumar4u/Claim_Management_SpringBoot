package com.cognizant.controllertest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.cognizant.controller.PolicyController;
import com.cognizant.dto.PolicyDto;
import com.cognizant.services.PolicyService;

public class PolicyControllerTest {

    @InjectMocks
    private PolicyController policyController;

    @Mock
    private PolicyService policyService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetAllPolicies_Positive() {
        List<PolicyDto> policyDtos = new ArrayList<>();
        policyDtos.add(new PolicyDto("KU12324", "Rakesh", "Kumar", LocalDate.now(),"rakesh123@gmail.com", "ABC123", false));
        when(policyService.getAllPolicies()).thenReturn(policyDtos);

        ResponseEntity<List<PolicyDto>> response = policyController.getAllPolicies();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(policyDtos, response.getBody());
    }

    @Test
    public void testInsertPolicy_Positive() {
        PolicyDto policyDto = new PolicyDto("KU12324", "Rakesh", "Kumar", LocalDate.now(),"rakesh123@gmail.com", "ABC123", false);
        when(policyService.insertPolicy(any(PolicyDto.class))).thenReturn(policyDto);

        ResponseEntity<PolicyDto> response = policyController.insertPolicy(policyDto);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(policyDto, response.getBody());
    }

}