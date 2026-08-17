package com.isha.job_portal.dto;

import lombok.Data;

@Data
public class UserRegisterRequest {
    private String name;
    private String email;
    private String password;
    private String role; // "APPLICANT" or "RECRUITER"
}