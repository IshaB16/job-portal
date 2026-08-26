package com.isha.job_portal.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
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
    public Page<JobResponse> getAllJobs(
            @RequestParam(required = false) String location,
            @RequestParam(required = false) String skills,
            @RequestParam(required = false) Double minSalary,
            @PageableDefault(size = 10, sort = "createdAt") Pageable pageable) {
        return jobService.getFilteredJobs(location, skills, minSalary, pageable);
    }
}