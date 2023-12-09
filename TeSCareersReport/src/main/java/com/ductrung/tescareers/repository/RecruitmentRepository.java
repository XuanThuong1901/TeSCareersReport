package com.ductrung.tescareers.repository;

import com.ductrung.tescareers.enitity.ApplicationHistories;
import com.ductrung.tescareers.enitity.Departments;
import com.ductrung.tescareers.enitity.Recruitments;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RecruitmentRepository extends JpaRepository<Recruitments, Integer> {

    List<Recruitments> findByDepartment(Departments department);
}
