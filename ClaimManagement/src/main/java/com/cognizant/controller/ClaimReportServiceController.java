package com.cognizant.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cognizant.services.ClaimReportService;

@RestController
@RequestMapping("/api/reports/claims")
public class ClaimReportServiceController {

    private final ClaimReportService claimReportService;

    @Autowired
    public ClaimReportServiceController(ClaimReportService claimReportService) {
        this.claimReportService = claimReportService;
    }

    @GetMapping("/pending-count")
    public ResponseEntity<Integer> getPendingClaimsCount(
             @RequestParam("month") int month,
             @RequestParam("year") int year) {
        int pendingClaimsCount = claimReportService.getPendingClaimsCount(month, year);
        return ResponseEntity.ok(pendingClaimsCount);
    }
 
    @GetMapping("/approved-amount")
    public ResponseEntity<Integer> getApprovedAmountByInsuranceCompany(
             @RequestParam("month") int month,
             @RequestParam("year") int year) {
        int approvedAmount = claimReportService.getApprovedAmountByInsuranceCompany(month, year);
        return ResponseEntity.ok(approvedAmount);
    }
}
