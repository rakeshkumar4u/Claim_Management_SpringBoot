package com.cognizant.servicetest;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.cognizant.repository.ClaimDetailsRepo;
import com.cognizant.services.impl.ClaimReportServiceImpl;

public class ClaimReportServiceImplTest {

    @Mock
    private ClaimDetailsRepo claimDetailsRepo;

    private ClaimReportServiceImpl claimReportService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        claimReportService = new ClaimReportServiceImpl(claimDetailsRepo);
    }

    @Test
    public void testGetPendingClaimsCount() {
        Mockito.when(claimDetailsRepo.countByClaimStatusAndDateOfAccidentYearMonth(Mockito.anyBoolean(),
        		Mockito.anyInt(), Mockito.anyInt()))
                .thenReturn(5);
        int result = claimReportService.getPendingClaimsCount(6, 2022);
        Assertions.assertEquals(5, result);
    }

    @Test
    public void testGetApprovedAmountByInsuranceCompany() {
        Mockito.when(claimDetailsRepo.getApprovedAmountBySurveyorByInsuranceCompanyApprovalAndDateOfAccidentYearMonth(Mockito.anyBoolean(),
        		Mockito.anyInt(), Mockito.anyInt()))
                .thenReturn(10000);
        int result = claimReportService.getApprovedAmountByInsuranceCompany(6, 2022);

        Assertions.assertEquals(10000, result);
    }
}