package com.cognizant.entities;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Surveyor {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="Surveyor_Id")
	private int surveyorId;
	
	@Column(name="First_Name")
	private String firstName;
	
	@Column(name="Last_Name")
	private String lastName;
	
	@Column(name="Estimate_Limt")
	private int estimateLimt;
	
	@OneToMany(mappedBy="surveyor")
	private List<ClaimDetails> claimDetails;
	
}
 
