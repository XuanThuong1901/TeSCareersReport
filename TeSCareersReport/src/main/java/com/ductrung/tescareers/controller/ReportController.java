package com.ductrung.tescareers.controller;

import com.ductrung.tescareers.enitity.Recruitments;
import com.ductrung.tescareers.model.response.DeptRecruitReportResponse;
import com.ductrung.tescareers.service.IDeptRecruitReportService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/report")
public class ReportController {

    private final IDeptRecruitReportService iDeptRecruitReportService;

//    @GetMapping("/{departmentId}")
//    public List<Recruitments> getByDepartment(@PathVariable(name = "departmentId") int departmentId){
//
//        List<Recruitments> recruitments = iDeptRecruitReportService.getRecruitments(departmentId);
//
//        return recruitments;
//    }

    @PostMapping("/{departmentId}")
    public List<DeptRecruitReportResponse> getReport(@PathVariable(name = "departmentId") int departmentId){

        List<DeptRecruitReportResponse> responses = iDeptRecruitReportService.deptRecruitReport(departmentId);

        return responses;
    }
}
