package com.isha.job_portal.dto;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class ApplicationResponse {
    private Long id;
    private String jobTitle;
    private String applicantEmail;
    private String status;
    private LocalDateTime appliedAt;
}