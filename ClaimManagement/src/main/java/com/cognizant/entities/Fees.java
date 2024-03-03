package com.cognizant.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Fees {
    
	@Id
	@Column(name="Fee_Id")
	private Integer FeeId;
	
	@Column(name="Estimate_Start_Limit")
	private Integer EstimateStartLimit;
	
	@Column(name="Estimate_End_Limit")
	private Integer EstimateEndLimit;
	
	@Column(name="Fees")
	private Integer fees;

}
