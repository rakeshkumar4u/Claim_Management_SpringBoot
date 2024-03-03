package com.cognizant.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import lombok.Data;


@Entity
@Data
public class Surveyor {

	@Id
	@Column(name="Surveyor_Id")
	private Integer Surveyorld;
	
	@Column(name="First_Name")
	private String FirstName;
	
	@Column(name="Last_Name")
	private String LastName;
	
	@Column(name="Estimate_Limt")
	private Integer EstimateLimt;		
	
}
