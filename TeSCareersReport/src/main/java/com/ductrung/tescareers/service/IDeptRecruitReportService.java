package com.ductrung.tescareers.service;

import com.ductrung.tescareers.enitity.Recruitments;
import com.ductrung.tescareers.model.request.ApplicationSubmissionRequest;
import com.ductrung.tescareers.model.response.DeptRecruitReportResponse;

import java.util.List;

public interface IDeptRecruitReportService {
    List<DeptRecruitReportResponse> deptRecruitReport(int departmentId);

}
