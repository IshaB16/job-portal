package com.isha.job_portal.controller;

import com.isha.job_portal.dto.ApplicationRequest;
import com.isha.job_portal.dto.ApplicationResponse;
import com.isha.job_portal.service.ApplicationService;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/applications")
public class ApplicationController {

    private final ApplicationService applicationService;

    public ApplicationController(ApplicationService applicationService) {
        this.applicationService = applicationService;
    }

    @PostMapping
    public ApplicationResponse apply(@RequestBody ApplicationRequest request, Authentication authentication) {
        String applicantEmail = authentication.getName();
        return applicationService.applyToJob(request, applicantEmail);
    }

    @GetMapping("/my")
    public List<ApplicationResponse> myApplications(Authentication authentication) {
        String applicantEmail = authentication.getName();
        return applicationService.getMyApplications(applicantEmail);
    }
}