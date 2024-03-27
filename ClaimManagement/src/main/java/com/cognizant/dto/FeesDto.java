package com.cognizant.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class FeesDto {
	
	private int feeId;
	
	@Min(value=0)
	private int estimateStartLimit;
    
	@Min(value=0)
	@Max(value=Integer.MAX_VALUE)
	private int estimateEndLimit;

	private int fees;

}
