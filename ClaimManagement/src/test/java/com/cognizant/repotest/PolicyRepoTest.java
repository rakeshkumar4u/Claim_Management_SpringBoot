package com.cognizant.repotest;
import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
//import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.cognizant.entities.Policy;
import com.cognizant.repository.PolicyRepo;

import java.util.Optional;
 
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
 
@DataJpaTest
public class PolicyRepoTest {
 
    @Mock
    private PolicyRepo policyRepo;
 
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