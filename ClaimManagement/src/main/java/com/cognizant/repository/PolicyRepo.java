package com.cognizant.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.cognizant.entities.Policy;

@Repository
public interface PolicyRepo extends JpaRepository<Policy, String> {

    @Query("SELECT CASE WHEN COUNT(p) > 0 THEN true ELSE false END FROM Policy p WHERE p.vehicleNo = :vehicleNo")
    boolean existsByVehicleNo(String vehicleNo);

}
