package com.isha.job_portal.dto;

import lombok.Data;

@Data
public class StatusUpdateRequest {
    private String status; // SHORTLISTED, REJECTED, HIRED
}