package com.ductrung.tescareers.service;

import com.ductrung.tescareers.enitity.*;
import com.ductrung.tescareers.model.request.ApplicationSubmissionRequest;
import com.ductrung.tescareers.model.response.DepartmentReportResponse;
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
    private final static String STATE_FINISHED = "Finished";

    @Override
    public List<DeptRecruitReportResponse> deptRecruitReport(int departmentId) {

        List<Recruitments> recruitments = getRecruitmentsByDepartment(departmentId);
        if(recruitments == null)
            return null;

        List<DeptRecruitReportResponse> responses = new ArrayList<>();

        for (Recruitments recruitment: recruitments) {
            DeptRecruitReportResponse response = calculateRatioDeptRecruit(recruitment);
            responses.add(response);
        }
        return responses;
    }

    @Override
    public DepartmentReportResponse departmentReport(int departmentId) {

        List<Recruitments> recruitments = getRecruitmentsByDepartment(departmentId);
        if(recruitments == null)
            return null;

        DepartmentReportResponse response = calculateRatioDepartment(recruitments);
        response.setIdDepartment(departmentId);
        return response;
    }

    private List<Recruitments> getRecruitmentsByDepartment(int departmentId){
        Departments department = departmentRepository.findById(departmentId).orElse(null);
        if(department == null)
            return null;

        List<Recruitments> recruitments = recruitmentRepository.findByDepartment(department);

        return recruitments;
    }

    private DeptRecruitReportResponse calculateRatioDeptRecruit(Recruitments recruitment){
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



    private DepartmentReportResponse calculateRatioDepartment(List<Recruitments> recruitments){
        int numberOfApplicants = 0;
        int numberOfPassingProfile = 0;
        int numberOfFailProfile = 0;
        int numberOfRejectedCandidates = 0;
        int numberOfHiredCandidates = 0;

        for (Recruitments recruitment: recruitments) {
            System.out.println(recruitment.getRecruitmentState().getRecruitmentStateName());
            if(recruitment.getJobPostings().size() == 0 || !recruitment.getRecruitmentState().getRecruitmentStateName().equalsIgnoreCase(STATE_FINISHED))
                continue;
            numberOfApplicants += recruitment.getApplicationSubmissions().size();

            HashMap<String, Integer> numberListCounter = counterApplications(recruitment.getApplicationSubmissions());
            System.out.println(numberListCounter);

            numberOfFailProfile += numberListCounter.get(NUMBER_OF_FAIL_PROFILE);
            numberOfPassingProfile += numberListCounter.get(NUMBER_OF_PASSING_PROFILE);
            numberOfRejectedCandidates += numberListCounter.get(NUMBER_OF_REJECTED_CANDIDATES);
            numberOfHiredCandidates += numberListCounter.get(NUMBER_OF_HIRED_CANDIDATES);
        }

        DepartmentReportResponse response = new DepartmentReportResponse();
        response.setNumberOfApplicants(numberOfApplicants);
        response.setNumberOfFailProfile(numberOfFailProfile);
        response.setNumberOfPassingProfile(numberOfPassingProfile);
        response.setNumberOfHiredCandidates(numberOfHiredCandidates);
        response.setNumberOfRejectedCandidates(numberOfRejectedCandidates);

        ;
//        response.setFailProfileRate(Math.round((float) (numberOfFailProfile / numberOfApplicants *100.0) / 100.0));
//        response.setFailProfileRate(Math.round((float) (numberOfFailProfile / numberOfApplicants *100.0) / 100.0));
//        response.setPassingProfileRate(Math.round((float) (numberOfPassingProfile / numberOfApplicants *100.0) / 100.0));
//        response.setHiredCandidatesRate(Math.round((float) (numberOfHiredCandidates / numberOfApplicants *100.0) / 100.0));
//        response.setRejectedCandidatesRate(Math.round((float) (numberOfRejectedCandidates / numberOfApplicants *100.0) / 100.0));
//        response.setHiredCandidatePassProfRate(Math.round((float) (numberOfHiredCandidates / numberOfPassingProfile *100.0) / 100.0));
//        response.setRejectedCandidatePassProfRate(Math.round((float) ((numberOfRejectedCandidates - numberOfFailProfile) / numberOfPassingProfile *100.0) / 100.0));

        response.setFailProfileRate((float) numberOfFailProfile / numberOfApplicants);
        response.setFailProfileRate((float) numberOfFailProfile / numberOfApplicants);
        response.setPassingProfileRate((float) numberOfPassingProfile / numberOfApplicants);
        response.setHiredCandidatesRate((float) numberOfHiredCandidates / numberOfApplicants);
        response.setRejectedCandidatesRate((float) numberOfRejectedCandidates / numberOfApplicants);
        response.setHiredCandidatePassProfRate((float) numberOfHiredCandidates / numberOfPassingProfile);
        response.setRejectedCandidatePassProfRate((float) (numberOfRejectedCandidates - numberOfFailProfile) / numberOfPassingProfile);

        return response;
    }
}
