package com.cognizant.entities;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List; 

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Policy {
    
	@Id
	@Column(name="Policy_No")
    private String policyNo;
 
    @Column(name = "Insured_First_Name")
    private String insuredFirstName;
 
    @Column(name = "Insured_Last_Name")
    private String insuredLastName;
 
    @Column(name = "Date_of_Insurance")
    private LocalDate dateOfInsurance;
 
    @Column(name = "Email_Id")
    private String emailId;
 
    @Column(name = "Vehicle_No")
    private String vehicleNo;
 
    @Column(name = "Status")
    private boolean status;
 
    //A Policy entity can have multiple ClaimDetails
    @OneToMany(mappedBy = "policy")
    private List<ClaimDetails> claimDetails;
  
}


