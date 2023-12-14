package com.ductrung.tescareers.model.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class DeptRecruitReportResponse {
    private InfoRecruitmentJob infoRecruitmentJob;

    private float passingProfileRate;
    private float failProfileRate;
    private float hiredCandidatesRate;
    private float rejectedCandidatesRate;

    private float completionRate;
}

