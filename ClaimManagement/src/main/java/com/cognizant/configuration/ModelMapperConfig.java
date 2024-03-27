package com.cognizant.configuration;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.cognizant.dto.ClaimDetailsDto;
import com.cognizant.dto.PolicyDto;
import com.cognizant.entities.ClaimDetails;

import org.modelmapper.convention.MatchingStrategies;
@Configuration
public class ModelMapperConfig {
	
	@Bean
	public ModelMapper modelMapper() {
		ModelMapper modelMapper = new ModelMapper();
		
// Set matching strategy to strict to avoid unintended mappings
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

// Add explicit mapping to resolve ambiguity for policyNo
		modelMapper.typeMap(ClaimDetails.class, ClaimDetailsDto.class)
		.addMapping(src -> src.getPolicy().getPolicyNo(),ClaimDetailsDto::setPolicyNo);

// Add explicit mapping for policyNo from PolicyDto to ClaimDetailsDto 
		modelMapper.typeMap(PolicyDto.class, ClaimDetailsDto.class)
		.addMapping(PolicyDto::getPolicyNo,ClaimDetailsDto::setPolicyNo);
		return modelMapper;
	}
}
