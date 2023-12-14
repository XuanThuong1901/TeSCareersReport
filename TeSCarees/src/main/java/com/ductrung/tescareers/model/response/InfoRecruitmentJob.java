package com.ductrung.tescareers.model.response;

import lombok.*;

import java.util.Date;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class InfoRecruitmentJob{
    private Integer recruitmentId;
    private String recruitmentTitle;
    private String description;
    private String jobPostingTitle;
    private Date createDateTime;
    private Date deadline;
    private Integer numberOfPosition;
    private String status;

    private Integer numberOfApplicants;
    private Integer numberOfPassingProfile;
    private Integer numberOfFailProfile;
    private Integer numberOfRejectedCandidates;
    private Integer numberOfHiredCandidates;
}