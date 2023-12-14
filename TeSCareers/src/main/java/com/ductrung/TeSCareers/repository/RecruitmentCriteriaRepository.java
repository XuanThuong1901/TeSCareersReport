package com.ductrung.tescareers.repository;

import com.ductrung.tescareers.enitity.ApplicationHistories;
import com.ductrung.tescareers.enitity.RecruitmentCriterias;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RecruitmentCriteriaRepository extends JpaRepository<RecruitmentCriterias, Integer> {
}
