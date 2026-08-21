package com.isha.job_portal.service;

import com.isha.job_portal.dto.JobRequest;
import com.isha.job_portal.dto.JobResponse;
import com.isha.job_portal.entity.Job;
import com.isha.job_portal.entity.User;
import com.isha.job_portal.repository.JobRepository;
import com.isha.job_portal.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JobService {

    private final JobRepository jobRepository;
    private final UserRepository userRepository;

    public JobService(JobRepository jobRepository, UserRepository userRepository) {
        this.jobRepository = jobRepository;
        this.userRepository = userRepository;
    }

    public JobResponse postJob(JobRequest request, String recruiterEmail) {
        User recruiter = userRepository.findByEmail(recruiterEmail)
                .orElseThrow(() -> new RuntimeException("Recruiter not found"));

        Job job = new Job();
        job.setTitle(request.getTitle());
        job.setDescription(request.getDescription());
        job.setLocation(request.getLocation());
        job.setSkillsRequired(request.getSkillsRequired());
        job.setSalary(request.getSalary());
        job.setPostedBy(recruiter);

        Job savedJob = jobRepository.save(job);
        return toResponse(savedJob);
    }

    public List<JobResponse> getAllJobs() {
        return jobRepository.findAll().stream().map(this::toResponse).toList();
    }

    private JobResponse toResponse(Job job) {
        JobResponse response = new JobResponse();
        response.setId(job.getId());
        response.setTitle(job.getTitle());
        response.setDescription(job.getDescription());
        response.setLocation(job.getLocation());
        response.setSkillsRequired(job.getSkillsRequired());
        response.setSalary(job.getSalary());
        response.setPostedByEmail(job.getPostedBy().getEmail());
        response.setCreatedAt(job.getCreatedAt());
        return response;
    }
}