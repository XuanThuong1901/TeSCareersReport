package com.ductrung.tescareers.model.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class DepartmentReportResponse {
    private Integer idDepartment;

    private Integer numberOfApplicants;
    private Integer numberOfPassingProfile;
    private Integer numberOfFailProfile;
    private Integer numberOfRejectedCandidates;
    private Integer numberOfHiredCandidates;

    private float passingProfileRate;
    private float failProfileRate;
    private float hiredCandidatesRate;
    private float rejectedCandidatesRate;
    private float hiredCandidatePassProfRate;
    private float rejectedCandidatePassProfRate;
}
