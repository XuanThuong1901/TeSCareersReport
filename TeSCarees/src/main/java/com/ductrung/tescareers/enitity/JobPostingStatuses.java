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
@Table(name = "JobPostingStatuses")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class JobPostingStatuses {

    @Id
    @Column(name = "JobPostingStatusId")
    private int jobPostingStatusId;

    @Column(name = "JobPostingStatusName")
    private String jobPostingStatusName;

    @JsonIgnore
    @OneToMany(mappedBy = "jobPostingStatus",fetch = FetchType.LAZY)
    private List<JobPostings> jobPostings;
}
