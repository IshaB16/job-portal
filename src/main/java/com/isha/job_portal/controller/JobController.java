package com.isha.job_portal.controller;

import com.isha.job_portal.dto.JobRequest;
import com.isha.job_portal.dto.JobResponse;
import com.isha.job_portal.service.JobService;
import jakarta.validation.Valid;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/jobs")
public class JobController {

    private final JobService jobService;

    public JobController(JobService jobService) {
        this.jobService = jobService;
    }

    @PostMapping
    public JobResponse postJob(@Valid @RequestBody JobRequest request, Authentication authentication) {
        String recruiterEmail = authentication.getName();
        return jobService.postJob(request, recruiterEmail);
    }

    @GetMapping
    public List<JobResponse> getAllJobs() {
        return jobService.getAllJobs();
    }
}