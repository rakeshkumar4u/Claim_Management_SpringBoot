package com.cognizant.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cognizant.entities.Surveyor;

@Repository
public interface SurveyorRepo extends JpaRepository<Surveyor,Integer> {

}
