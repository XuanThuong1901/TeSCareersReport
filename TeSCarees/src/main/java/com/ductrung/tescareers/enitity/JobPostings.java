package com.ductrung.tescareers.enitity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "JobPostings")
public class JobPostings {
    @Id
    @Column(name = "JobPostingId")
    private int jobPostingId;

    @Column(name = "JobPostingTitle")
    private String jobPostingTitle;

    @Column(name = "Responsibilities")
    private String responsibilities;

    @Column(name = "Deadline")
    private Date deadline;

    @Column(name = "CreatedDateTime")
    private Date createdDateTime;

    @Column(name = "PublisherId")
    private int publisherId;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "RecruitmentId")
    private Recruitments recruitment;

    @JsonIgnoreProperties("jobPostings")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "StatusId")
    private JobPostingStatuses jobPostingStatus;
}
