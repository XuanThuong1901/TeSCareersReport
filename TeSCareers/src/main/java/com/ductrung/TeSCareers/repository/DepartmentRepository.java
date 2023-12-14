package com.ductrung.tescareers.repository;

import com.ductrung.tescareers.enitity.ApplicationHistories;
import com.ductrung.tescareers.enitity.Departments;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartmentRepository extends JpaRepository<Departments, Integer> {
}
