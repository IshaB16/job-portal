package com.isha.job_portal.repository;

import com.isha.job_portal.entity.Job;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface JobRepository extends JpaRepository<Job, Long> {

    @Query("SELECT j FROM Job j WHERE " +
            "(:location IS NULL OR LOWER(j.location) LIKE LOWER(CONCAT('%', :location, '%'))) AND " +
            "(:skills IS NULL OR LOWER(j.skillsRequired) LIKE LOWER(CONCAT('%', :skills, '%'))) AND " +
            "(:minSalary IS NULL OR j.salary >= :minSalary)")
    Page<Job> findJobsWithFilters(@Param("location") String location,
                                  @Param("skills") String skills,
                                  @Param("minSalary") Double minSalary,
                                  Pageable pageable);
}