package com.cognizant.repository;


import org.springframework.data.jpa.repository.JpaRepository;


import com.cognizant.entities.Surveyor;

public interface SurveyorRepo extends JpaRepository<Surveyor,Integer> {

}
