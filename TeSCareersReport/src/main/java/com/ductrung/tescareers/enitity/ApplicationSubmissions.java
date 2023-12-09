package com.ductrung.tescareers.enitity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "ApplicationSubmissions")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})

public class ApplicationSubmissions {

    @Id
    @Column(name = "ApplicationSubmissionId")
    private Integer applicationSubmissionId;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "RecruitmentId")
    private Recruitments recruitment;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "CandidateId")
    private Candidates candidate;

    @JsonIgnoreProperties("applicationSubmissions")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "ApplicationStatusId")
    private ApplicationStatuses applicationStatus;

    @JsonIgnoreProperties("applicationSubmissions")
    @OneToMany(mappedBy = "applicationSubmission")
    private List<ApplicationHistories> applicationHistories;

}
