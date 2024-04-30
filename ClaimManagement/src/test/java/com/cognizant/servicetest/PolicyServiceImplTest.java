package com.cognizant.servicetest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;

import com.cognizant.dto.PolicyDto;
import com.cognizant.entities.Policy;
import com.cognizant.exception.VehicleNoAlreadyExistsException;
import com.cognizant.repository.PolicyRepo;
import com.cognizant.services.impl.PolicyServiceImpl;
import java.util.ArrayList;
import java.util.List;

public class PolicyServiceImplTest {

    @Mock
    private PolicyRepo policyRepo;

    @Mock
    private ModelMapper modelMapper;

    private PolicyServiceImpl policyService;

	@BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        policyService = new PolicyServiceImpl(policyRepo, modelMapper);
    }


    @Test
    public void testInsertPolicy_WhenVehicleNoExists() {
        PolicyDto policyDto = new PolicyDto();
        policyDto.setVehicleNo("ABC123");
        policyDto.setDateOfInsurance(null);
        
        // Mock the behavior of policyRepo.existsByVehicleNo()
        Mockito.when(policyRepo.existsByVehicleNo("ABC123")).thenReturn(true);
        Assertions.assertThrows(VehicleNoAlreadyExistsException.class, () -> policyService.insertPolicy(policyDto));
    }

    @Test
    public void testGetAllPolicies() {
        List<Policy> policies = new ArrayList<>();
        policies.add(new Policy());
        policies.add(new Policy());

        Mockito.when(policyRepo.findAll()).thenReturn(policies);
        Mockito.when(modelMapper.map(Mockito.any(Policy.class), Mockito.eq(PolicyDto.class)))
                .thenReturn(new PolicyDto());
        List<PolicyDto> result = policyService.getAllPolicies();

        Assertions.assertEquals(policies.size(), result.size());
    }
}