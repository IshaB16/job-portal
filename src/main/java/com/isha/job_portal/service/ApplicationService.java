package com.isha.job_portal.service;

import com.isha.job_portal.dto.ApplicationRequest;
import com.isha.job_portal.dto.ApplicationResponse;
import com.isha.job_portal.entity.Application;
import com.isha.job_portal.entity.Job;
import com.isha.job_portal.entity.User;
import com.isha.job_portal.repository.ApplicationRepository;
import com.isha.job_portal.repository.JobRepository;
import com.isha.job_portal.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ApplicationService {

    private final ApplicationRepository applicationRepository;
    private final JobRepository jobRepository;
    private final UserRepository userRepository;

    public ApplicationService(ApplicationRepository applicationRepository, JobRepository jobRepository, UserRepository userRepository) {
        this.applicationRepository = applicationRepository;
        this.jobRepository = jobRepository;
        this.userRepository = userRepository;
    }

    public ApplicationResponse applyToJob(ApplicationRequest request, String applicantEmail) {
        User applicant = userRepository.findByEmail(applicantEmail)
                .orElseThrow(() -> new RuntimeException("Applicant not found"));

        Job job = jobRepository.findById(request.getJobId())
                .orElseThrow(() -> new RuntimeException("Job not found"));

        Application application = new Application();
        application.setJob(job);
        application.setApplicant(applicant);

        Application saved = applicationRepository.save(application);
        return toResponse(saved);
    }

    public List<ApplicationResponse> getMyApplications(String applicantEmail) {
        User applicant = userRepository.findByEmail(applicantEmail)
                .orElseThrow(() -> new RuntimeException("Applicant not found"));

        return applicationRepository.findByApplicantId(applicant.getId())
                .stream().map(this::toResponse).toList();
    }

    private ApplicationResponse toResponse(Application application) {
        ApplicationResponse response = new ApplicationResponse();
        response.setId(application.getId());
        response.setJobTitle(application.getJob().getTitle());
        response.setApplicantEmail(application.getApplicant().getEmail());
        response.setStatus(application.getStatus().name());
        response.setAppliedAt(application.getAppliedAt());
        return response;
    }
}