package com.ductrung.tescareers.service;

import com.ductrung.tescareers.enitity.Departments;
import com.ductrung.tescareers.model.response.DepartmentResponse;

import java.util.List;

public interface IDepartmentService {
    List<DepartmentResponse> getAllDepartment();
}
