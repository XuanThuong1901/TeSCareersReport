package com.ductrung.tescareers.controller;

import com.ductrung.tescareers.model.response.DepartmentResponse;
import com.ductrung.tescareers.service.IDepartmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/department")
public class DepartmentController {
    private final IDepartmentService iDepartmentService;

    @PostMapping("")
    public List<DepartmentResponse> getAllDepartment(){
        return iDepartmentService.getAllDepartment();
    }
}
