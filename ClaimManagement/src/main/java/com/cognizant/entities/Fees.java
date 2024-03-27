package com.cognizant.entities;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Fees {
    
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="Fee_Id")
	private int feeId;
	
	@Column(name="Estimate_Start_Limit")
	private int estimateStartLimit;
	
	@Column(name="Estimate_End_Limit")
	private int estimateEndLimit;
	
	@Column(name="Fees")
	private int fees;

}
