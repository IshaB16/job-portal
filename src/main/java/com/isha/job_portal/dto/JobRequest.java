package com.isha.job_portal.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class JobRequest {

    @NotBlank(message = "Title is required")
    private String title;

    @NotBlank(message = "Description is required")
    private String description;

    @NotBlank(message = "Location is required")
    private String location;

    @NotBlank(message = "Skills required field cannot be empty")
    private String skillsRequired;

    @Positive(message = "Salary must be greater than zero")
    private Double salary;
}