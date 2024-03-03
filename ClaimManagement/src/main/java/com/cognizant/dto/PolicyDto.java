package com.cognizant.dto;

import java.sql.Date;

import jakarta.validation.constraints.Size;
import lombok.Data;


@Data
public class PolicyDto {
	
	@Size(min=10)
	private String PolicyNo;
	
	@Size(min=5)
	private String InsuredFirstName;
	
	@Size(min=5)
	private String InsuredLastName;
    
	
	private Date DateOfInsurance;
 
	private String EmailId;

	private String VehicleNo;

	private Boolean status;

}
