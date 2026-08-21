package com.isha.job_portal.dto;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class JobResponse {
    private Long id;
    private String title;
    private String description;
    private String location;
    private String skillsRequired;
    private Double salary;
    private String postedByEmail;
    private LocalDateTime createdAt;
}