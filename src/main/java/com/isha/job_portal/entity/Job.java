package com.isha.job_portal.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Entity
@Table(name = "jobs")
@Data
public class Job {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    @Column(length = 2000)
    private String description;

    private String location;

    private String skillsRequired;

    private Double salary;

    @ManyToOne
    @JoinColumn(name = "posted_by")
    private User postedBy;

    private LocalDateTime createdAt;

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
    }
}