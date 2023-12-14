package com.ductrung.tescareers.service;

import com.ductrung.tescareers.enitity.Departments;
import com.ductrung.tescareers.model.response.DepartmentResponse;
import com.ductrung.tescareers.repository.DepartmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DepartmentServiceImpl implements IDepartmentService{

    private final DepartmentRepository departmentRepository;

    @Override
    public List<DepartmentResponse> getAllDepartment() {
        List<Departments> departments = departmentRepository.findAll();
        List<DepartmentResponse> responses = new ArrayList<>();

        for (Departments department: departments) {
            DepartmentResponse response = new DepartmentResponse(department.getDepartmentId(), department.getDepartmentName());
            responses.add(response);
        }
        return responses;
    }
}
