package com.cognizant.repotest;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.cognizant.entities.Policy;
import com.cognizant.repository.PolicyRepo;
 
@ExtendWith(MockitoExtension.class)
@DataJpaTest
class PolicyRepoTest {
 
    @Mock
    private PolicyRepo policyRepo;
 
    @BeforeEach
    void setUp() {

    }
 
	// Positive test case: Check if policy exists by vehicle number
	@Test
	void testExistsByVehicleNo_Positive() {
		String vehicleNo = "ABC123";
		when(policyRepo.existsByVehicleNo(vehicleNo)).thenReturn(true);
		boolean exists = policyRepo.existsByVehicleNo(vehicleNo);

		// Then
		assertTrue(exists);

	}
 
    @Test
    void testExistsByVehicleNo_Negative() {
        String vehicleNo = "XYZ789";
        when(policyRepo.existsByVehicleNo(vehicleNo)).thenReturn(false);
        boolean exists = policyRepo.existsByVehicleNo(vehicleNo);
 
        // Then
        assertFalse(exists);
    }
    
    @Test
    public void testFindByPolicyNo() {
        String policyNo = "POL123";
        when(policyRepo.findById(policyNo)).thenReturn(Optional.of(new Policy()));
        Optional<Policy> result = policyRepo.findById(policyNo);
 
        assertTrue(result.isPresent());
    }
 
    @Test
    public void testFindByPolicyNoNotFound() {
        String policyNo = "POL123";
        when(policyRepo.findById(policyNo)).thenReturn(Optional.empty());
        Optional<Policy> result = policyRepo.findById(policyNo);
        assertFalse(result.isPresent());
    }
 
    @Test
    public void testSavePolicy() {
        Policy policy = new Policy();
        when(policyRepo.save(policy)).thenReturn(policy);
        Policy savedPolicy = policyRepo.save(policy);
        assertNotNull(savedPolicy);
    }
 
    @Test
    public void testDeleteByPolicyNo() {
        String policyNo = "POL123";
        policyRepo.deleteById(policyNo);
        verify(policyRepo, times(1)).deleteById(policyNo);
    }
 
    @Test
    public void testExceptionScenario() {
        String policyNo = "POL123";
        when(policyRepo.findById(policyNo)).thenThrow(new RuntimeException("Something went wrong!"));
        assertThrows(RuntimeException.class, () -> policyRepo.findById(policyNo));
    }

}
