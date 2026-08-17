package com.isha.job_portal.entity;

import com.isha.job_portal.entity.Job;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface JobRepository extends JpaRepository<Job, Long> {
    List<Job> findByLocation(String location);
}