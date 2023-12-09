package com.ductrung.tescareers.service;

import com.ductrung.tescareers.enitity.*;
import com.ductrung.tescareers.model.request.ApplicationSubmissionRequest;
import com.ductrung.tescareers.model.response.DeptRecruitReportResponse;
import com.ductrung.tescareers.model.response.InfoRecruitmentJob;
import com.ductrung.tescareers.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DeptRecruitReportServiceImpl implements IDeptRecruitReportService{

    private final RecruitmentRepository recruitmentRepository;
    private final DepartmentRepository departmentRepository;

    private final static String NUMBER_OF_APPLICANTS = "Applied";
    private final static String NUMBER_OF_PASSING_PROFILE = "Interview Scheduled";
    private final static String NUMBER_OF_FAIL_PROFILE = "fail";
    private final static String NUMBER_OF_REJECTED_CANDIDATES = "Rejected";
    private final static String NUMBER_OF_HIRED_CANDIDATES = "Hired";

    @Override
    public List<DeptRecruitReportResponse> deptRecruitReport(int departmentId) {
        Departments department = departmentRepository.findById(departmentId).orElse(null);
        if(department == null)
            return null;

        List<Recruitments> recruitments = recruitmentRepository.findByDepartment(department);
        if(recruitments == null)
            return null;

        List<DeptRecruitReportResponse> responses = new ArrayList<>();

        for (Recruitments recruitment: recruitments) {
            DeptRecruitReportResponse response = calculateRatio(recruitment);
            responses.add(response);
        }
        return responses;
    }

    private DeptRecruitReportResponse calculateRatio(Recruitments recruitment){
        DeptRecruitReportResponse response = new DeptRecruitReportResponse();
        InfoRecruitmentJob infoRecruitmentJob = InfoRecruitmentJob.builder()
                .recruitmentId(recruitment.getRecruitmentId())
                .recruitmentTitle(recruitment.getRecruitmentTitle())
                .description(recruitment.getDescription())
                .numberOfPosition(recruitment.getNumberOfPosition())
                .numberOfApplicants(recruitment.getApplicationSubmissions().size())
                .status(recruitment.getRecruitmentState().getRecruitmentStateName())
                .build();

        if(recruitment.getJobPostings().size() == 0){
            response.setHiredCandidatesRate(0);
            response.setRejectedCandidatesRate(0);
            response.setFailProfileRate(0);
            response.setPassingProfileRate(0);

        }
        else {
            infoRecruitmentJob.setJobPostingTitle(recruitment.getJobPostings().get(0).getJobPostingTitle());
            infoRecruitmentJob.setCreateDateTime(recruitment.getJobPostings().get(0).getCreatedDateTime());
            infoRecruitmentJob.setDeadline(recruitment.getJobPostings().get(0).getDeadline());

            HashMap<String, Integer> numberListCounter = counterApplications(recruitment.getApplicationSubmissions());
            infoRecruitmentJob.setNumberOfPassingProfile(numberListCounter.get(NUMBER_OF_PASSING_PROFILE));
            infoRecruitmentJob.setNumberOfFailProfile(numberListCounter.get(NUMBER_OF_FAIL_PROFILE));
            infoRecruitmentJob.setNumberOfHiredCandidates(numberListCounter.get(NUMBER_OF_HIRED_CANDIDATES));
            infoRecruitmentJob.setNumberOfRejectedCandidates(numberListCounter.get(NUMBER_OF_REJECTED_CANDIDATES));

            response.setPassingProfileRate((float) infoRecruitmentJob.getNumberOfPassingProfile() / infoRecruitmentJob.getNumberOfApplicants());
            response.setFailProfileRate((float) infoRecruitmentJob.getNumberOfFailProfile() / infoRecruitmentJob.getNumberOfApplicants());
            response.setHiredCandidatesRate((float) infoRecruitmentJob.getNumberOfHiredCandidates() / infoRecruitmentJob.getNumberOfPassingProfile());
            response.setRejectedCandidatesRate((float) infoRecruitmentJob.getNumberOfRejectedCandidates() / infoRecruitmentJob.getNumberOfApplicants());

            response.setCompletionRate((float) infoRecruitmentJob.getNumberOfHiredCandidates() / infoRecruitmentJob.getNumberOfPosition());
        }
        response.setInfoRecruitmentJob(infoRecruitmentJob);

        return response;
    }

    // Counter Application
    private HashMap<String, Integer> counterApplications( List<ApplicationSubmissions> applicationSubmissions){

//        int[] numberListCounter = {0, 0, 0};
        HashMap<String, Integer> numberListCounter = new HashMap<String, Integer>();
        numberListCounter.put(NUMBER_OF_PASSING_PROFILE, 0);
        numberListCounter.put(NUMBER_OF_REJECTED_CANDIDATES, 0);
        numberListCounter.put(NUMBER_OF_FAIL_PROFILE, 0);
        numberListCounter.put(NUMBER_OF_HIRED_CANDIDATES, 0);

        for (ApplicationSubmissions applicationSubmission: applicationSubmissions) {
            if(applicationSubmission.getApplicationStatus().getApplicationStatusName().equals(NUMBER_OF_HIRED_CANDIDATES)){
                numberListCounter.put(NUMBER_OF_HIRED_CANDIDATES, numberListCounter.get(NUMBER_OF_HIRED_CANDIDATES)+1);
                numberListCounter.put(NUMBER_OF_PASSING_PROFILE, numberListCounter.get(NUMBER_OF_PASSING_PROFILE)+1);
            }
            else if(applicationSubmission.getApplicationStatus().getApplicationStatusName().equals(NUMBER_OF_REJECTED_CANDIDATES)){
                numberListCounter.put(NUMBER_OF_REJECTED_CANDIDATES, numberListCounter.get(NUMBER_OF_REJECTED_CANDIDATES)+1);

                if(applicationSubmission.getApplicationHistories().size() > 2)
                    numberListCounter.put(NUMBER_OF_PASSING_PROFILE, numberListCounter.get(NUMBER_OF_PASSING_PROFILE)+1);
                else if(applicationSubmission.getApplicationHistories().size() == 2)
                    numberListCounter.put(NUMBER_OF_FAIL_PROFILE, numberListCounter.get(NUMBER_OF_FAIL_PROFILE)+1);
            }
        }
        return numberListCounter;
    }

//    private String checkStatusApplication(List<ApplicationHistories> applicationHistories){
//        String status = "";
//
//        for (ApplicationHistories applicationHistory: applicationHistories) {
//            if(applicationHistory.getApplicationStatus().getApplicationStatusName().equals(NUMBER_OF_PASSING_PROFILE)){
//                status = NUMBER_OF_PASSING_PROFILE;
//            }
//            if(applicationHistory.getApplicationStatus().getApplicationStatusName().equalsIgnoreCase(NUMBER_OF_HIRED_CANDIDATES))
//                status = NUMBER_OF_HIRED_CANDIDATES;
//            if(applicationHistory.getApplicationStatus().getApplicationStatusName().equalsIgnoreCase(NUMBER_OF_REJECTED_CANDIDATES))
//                status = NUMBER_OF_REJECTED_CANDIDATES;
//        }
//
//        return status;
//    }

}
