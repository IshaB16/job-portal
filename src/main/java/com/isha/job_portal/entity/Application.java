package com.isha.job_portal.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Entity
@Table(name = "applications")
@Data
public class Application {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "job_id")
    private Job job;

    @ManyToOne
    @JoinColumn(name = "applicant_id")
    private User applicant;

    @Enumerated(EnumType.STRING)
    private ApplicationStatus status;

    private LocalDateTime appliedAt;

    @PrePersist
    protected void onCreate() {
        this.appliedAt = LocalDateTime.now();
        this.status = ApplicationStatus.APPLIED; // default status when created
    }
}