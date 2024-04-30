package com.cognizant.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cognizant.services.ClaimReportService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/reports/claims")
@Tag(name="ClaimReportService Controller", description="ClaimReportService Controller REST API")
public class ClaimReportServiceController {

    private final ClaimReportService claimReportService;

    @Autowired
    public ClaimReportServiceController(ClaimReportService claimReportService) {
        this.claimReportService = claimReportService;
    }

    @GetMapping("/pending-count")
    @Operation(description = "Get Pending Claims Count in a particular Month and year")
    public ResponseEntity<Integer> getPendingClaimsCount(
             @RequestParam("month") int month,
             @RequestParam("year") int year) {
        int pendingClaimsCount = claimReportService.getPendingClaimsCount(month, year);
        return ResponseEntity.ok(pendingClaimsCount);
    }
 
    @GetMapping("/approved-amount")
    @Operation(description = "Get Approved Amount by Insurance Company in a particular Month and year")
    public ResponseEntity<Integer> getApprovedAmountByInsuranceCompany(
             @RequestParam("month") int month,
             @RequestParam("year") int year) {
        int approvedAmount = claimReportService.getApprovedAmountByInsuranceCompany(month, year);
        return ResponseEntity.ok(approvedAmount);
    }
}
