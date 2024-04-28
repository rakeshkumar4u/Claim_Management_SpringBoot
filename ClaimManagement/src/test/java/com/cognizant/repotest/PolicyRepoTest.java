package com.cognizant.repotest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.cognizant.entities.Policy;
import com.cognizant.repository.PolicyRepo;

@DataJpaTest
public class PolicyRepoTest {

    @Autowired
    private PolicyRepo policyRepo;

    @Test
    public void testExistsByVehicleNo_WhenPolicyExists() {
        // Create a Policy object and save it to the database
        Policy policy = new Policy();
        policy.setVehicleNo("ABC123");
        policyRepo.save(policy);

        // Call the existsByVehicleNo() method
        boolean result = policyRepo.existsByVehicleNo("ABC123");

        // Assert the result
        Assertions.assertTrue(result);
    }
}