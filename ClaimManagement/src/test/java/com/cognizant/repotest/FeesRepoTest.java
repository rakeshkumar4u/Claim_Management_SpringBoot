package com.cognizant.repotest;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import com.cognizant.entities.Fees;
import com.cognizant.repository.FeesRepo;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Optional;
 
@ExtendWith(MockitoExtension.class)
public class FeesRepoTest {
 
    @Mock
    private FeesRepo feesRepo;
 
    @Test
    public void testFindByFeeId() {
        int feeId = 1;
        when(feesRepo.findById(feeId)).thenReturn(Optional.of(new Fees()));
        Optional<Fees> result = feesRepo.findById(feeId);
        assertTrue(result.isPresent());
    }
 
    // -ve= id not found
    @Test
    public void testFindByFeeIdNotFound() {
        int feeId = 1;
        when(feesRepo.findById(feeId)).thenReturn(Optional.empty());
        Optional<Fees> result = feesRepo.findById(feeId);
        assertFalse(result.isPresent());
    }
 
    @Test
    public void testSaveFees() {
        Fees fees = new Fees();
        when(feesRepo.save(fees)).thenReturn(fees);
        Fees savedFees = feesRepo.save(fees);
        assertNotNull(savedFees);
    }
 
    @Test
    public void testDeleteByFeeId() {
        int feeId = 1;
        feesRepo.deleteById(feeId);
        verify(feesRepo, times(1)).deleteById(feeId);
    }
 
    @Test
    public void testExceptionScenario() {
        int feeId = 1;
        when(feesRepo.findById(feeId)).thenThrow(new RuntimeException("Something went wrong!"));
        assertThrows(RuntimeException.class, () -> feesRepo.findById(feeId));
    }
}
