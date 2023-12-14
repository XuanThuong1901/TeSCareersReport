package com.ductrung.tescareers.enitity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Recruitments")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Recruitments {
    @Id
    @Column(name = "RecruitmentId")
    private int recruitmentId;

    @Column(name = "RecruitmentTitle")
    private String recruitmentTitle;

    @Column(name = "JobDescriptionId")
    private int jobDescriptionId;

    @Column(name = "NumberOfPosition")
    private int numberOfPosition;

//    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "StartDate")
    private java.sql.Date startDate;

    @Column(name = "JobJustificationId")
    private int jobJustificationId;

    @Column(name = "Description")
    private String description;

    @Column(name = "CreatorId")
    private int creatorId;

    @Column(name = "ApproverId")
    private int approverId;

    @Column(name = "CreatedDateTime")
    private Date createdDateTime;

    @JsonIgnoreProperties("recruitments")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "DepartmentId")
    private Departments department;

    @JsonIgnoreProperties("recruitments")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "RecruitmentStateId")
    private RecruitmentStates recruitmentState;

    @JsonIgnoreProperties("recruitment")
    @OneToMany(mappedBy = "recruitment",fetch = FetchType.LAZY)
    private List<RecruitmentCriterias> recruitmentCriterias;

    @JsonIgnoreProperties("recruitment")
    @OneToMany(mappedBy = "recruitment",fetch = FetchType.LAZY)
    private List<ApplicationSubmissions> applicationSubmissions;

    @JsonIgnoreProperties("recruitment")
    @OneToMany(mappedBy = "recruitment",fetch = FetchType.LAZY)
    private List<JobPostings> jobPostings;
}
