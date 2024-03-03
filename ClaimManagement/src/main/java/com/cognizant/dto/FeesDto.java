package com.cognizant.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.Data;


@Data
public class FeesDto {
	
	private Integer FeeId;
	
	@Min(value=0)
	private Integer EstimateStartLimit;
    
	@Min(value=0)
	@Max(value=Integer.MAX_VALUE)
	private Integer EstimateEndLimit;

	private Integer fees;

}
