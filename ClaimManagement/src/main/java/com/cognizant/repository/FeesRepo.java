package com.cognizant.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cognizant.entities.Fees;

//@Repository
public interface FeesRepo extends JpaRepository<Fees,Integer> {

}
