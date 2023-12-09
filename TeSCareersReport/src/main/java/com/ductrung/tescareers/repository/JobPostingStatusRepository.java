package com.ductrung.tescareers.repository;

import com.ductrung.tescareers.enitity.ApplicationHistories;
import com.ductrung.tescareers.enitity.JobPostingStatuses;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JobPostingStatusRepository extends JpaRepository<JobPostingStatuses, Integer> {
}
