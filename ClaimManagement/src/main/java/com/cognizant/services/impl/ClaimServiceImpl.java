package com.cognizant.services.impl;
 
import java.time.LocalDate;
 
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
 
import com.cognizant.dto.ClaimDetailsDto;
import com.cognizant.entities.ClaimDetails;
import com.cognizant.entities.Policy;
import com.cognizant.entities.Surveyor;
import com.cognizant.exception.InvalidPolicyException;
import com.cognizant.exception.InvalidSurveyorException;
import com.cognizant.exception.MaximumClaimLimitReachedException;
import com.cognizant.exception.NoEligibleSurveyorException;
import com.cognizant.exception.ResourceNotFoundException;
import com.cognizant.repository.ClaimDetailsRepo;
import com.cognizant.repository.PolicyRepo;
import com.cognizant.repository.SurveyorRepo;
import com.cognizant.services.ClaimService;
import com.cognizant.utilities.ClaimIdGenerator;
import jakarta.transaction.Transactional;
 
import java.util.ArrayList;
import java.util.List;
@Service
public class ClaimServiceImpl implements ClaimService {
        private final PolicyRepo policyRepo;
		private final ClaimDetailsRepo claimDetailsRepo;
		private final SurveyorRepo surveyorRepo;
		private final ModelMapper modelMapper;
		
		public ClaimServiceImpl(PolicyRepo policyRepo,ClaimDetailsRepo claimDetailsRepo,SurveyorRepo surveyorRepo,ModelMapper modelMapper)
		{
			this.policyRepo=policyRepo;
			this.claimDetailsRepo=claimDetailsRepo;
			this.surveyorRepo=surveyorRepo;
			this.modelMapper=modelMapper;	
		}		
 //==================================================================================================================
		@Override
		@Transactional
		public ClaimDetailsDto insertClaim(ClaimDetailsDto claimDetailsDto) {
		    // Retrieve Policy entity
		    Policy policy = policyRepo.findById(claimDetailsDto.getPolicy().getPolicyNo())
		            .orElseThrow(() -> new InvalidPolicyException("Policy not found for ID: " + claimDetailsDto.getPolicy().getPolicyNo()));
 
		    // Retrieve Surveyor entity
		    Surveyor surveyor = surveyorRepo.findById(claimDetailsDto.getSurveyor().getSurveyorId())
		            .orElseThrow(() -> new InvalidSurveyorException("Surveyor not found for ID: " + claimDetailsDto.getSurveyor().getSurveyorId()));
 
		    // Check for existing claims in current year
		    int year = LocalDate.now().getYear();
		    List<ClaimDetails> existingClaims = claimDetailsRepo.findByPolicyNoAndDateOfAccidentYear(policy.getPolicyNo(), year);
		    if (!existingClaims.isEmpty()) {
		        throw new MaximumClaimLimitReachedException("Maximum claim limit reached for this year");
		    }
 
		    // Generate claimID AUTO
		    String claimId = ClaimIdGenerator.generateClaimId(policy.getPolicyNo(), claimDetailsDto.getDateOfAccident());
		    claimDetailsDto.setClaimId(claimId);
 
		    // Calculate surveyor fees
		    int estimatedLoss = claimDetailsDto.getEstimatedLoss();
		    claimDetailsDto.setSurveyorFees(calculateSurveyorFee(estimatedLoss));
 
		    // Map DTO to entity and save
		    ClaimDetails claimDetails = modelMapper.map(claimDetailsDto, ClaimDetails.class);
		    claimDetails.setPolicy(policy);
		    claimDetails.setSurveyor(surveyor);
		    claimDetailsRepo.save(claimDetails);
 
		    // Return mapped DTO
		    return modelMapper.map(claimDetails, ClaimDetailsDto.class);
		}
 
//========================================================================================================== 
    
    @Override
    @Transactional
    public ClaimDetailsDto updateClaim(ClaimDetailsDto claimDetailsDto, String claimId) {
    	
    	//Retrieve existing class details
        ClaimDetails existingClaim = claimDetailsRepo.findById(claimId)
                .orElseThrow(() -> new ResourceNotFoundException("Claim not found with ID"+claimId));
 
        //Update fields
        existingClaim.setEstimatedLoss(claimDetailsDto.getEstimatedLoss());
        existingClaim.setDateOfAccident(claimDetailsDto.getDateOfAccident());
        existingClaim.setClaimStatus(claimDetailsDto.isClaimStatus());
 
        //Recalculate surveyor fees if estimated loss changes
        if (existingClaim.getEstimatedLoss() != claimDetailsDto.getEstimatedLoss()) {
            existingClaim.setSurveyorFees(calculateSurveyorFee(claimDetailsDto.getEstimatedLoss()));
        }
 
        ClaimDetails updatedClaim = claimDetailsRepo.save(existingClaim);
 
        return modelMapper.map(updatedClaim, ClaimDetailsDto.class);
    }
    
    @Override
    @Transactional
	public List<ClaimDetailsDto> getAllClaims() {
		List<ClaimDetails> claims=this.claimDetailsRepo.findAll();
		 List<ClaimDetailsDto> claimDetailsDtos = new ArrayList<>();
	        for (ClaimDetails claim : claims) {
	            claimDetailsDtos.add(modelMapper.map(claim, ClaimDetailsDto.class));
	        }
	        return claimDetailsDtos;
	}
//=================================IMPL Business Logics====================================================
 
    public Surveyor findEligibleSurveyor(int estimatedLoss) {  	
        List<Surveyor> surveyors = surveyorRepo.findAll();
        
        // Find a Surveyor whose estimateLimit is greater than or equal to the estimated Loss
        for (Surveyor surveyor : surveyors) {
            if (surveyor.getEstimateLimt() >= estimatedLoss) {
                return surveyor;
            }
        }
        // If no suitable surveyor found
        throw new NoEligibleSurveyorException("No surveyor found with a high enough estimate limit");
    }
 
    private int calculateSurveyorFee(int estimatedLoss) {
        if (estimatedLoss >= 5000 && estimatedLoss < 10000) {
            return 1000;
        } else if (estimatedLoss >= 10000 && estimatedLoss < 20000) {
            return 2000;
        } else if (estimatedLoss >= 20000 && estimatedLoss < 70000) {
            return 7000;
        }
        return 0;
    }
}