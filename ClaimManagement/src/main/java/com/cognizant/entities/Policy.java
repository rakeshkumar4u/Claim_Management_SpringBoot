package com.cognizant.entities;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Policy {
	
	
	@Id
	@Column(name="Policy_No",length=7)
	private String PolicyNo;
	
	@Column(name="Insured_First_Name")
	private String InsuredFirstName;
	
	@Column(name="Insured_Last_Name")
	private String InsuredLastName;
	
	@Column(name="Date_Of_Insurance")
	private LocalDate DateOfInsurance;
	
	@Column(name="Email_Id")
	private String EmailId;
	
	@Column(name="Vehicle_No")
	private String VehicleNo;
	
	@Column(name="Status")
	private Boolean status;
}

