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
@Table(name = "Candidates")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})

public class Candidates {
    @Id
    @Column(name = "CandidateId")
    private int candidateId;

    @Column(name = "Bio")
    private String bio;

    @Column(name = "School")
    private String school;

    @Column(name = "UserId")
    private int userId;

    @Column(name = "QualificationId")
    private int qualificationId;

    @Column(name = "ExperienceId")
    private int experienceId;

    @JsonIgnore
    @OneToMany(mappedBy = "candidate",fetch = FetchType.LAZY)
    private List<ApplicationSubmissions> applicationSubmissions;

//    @OneToMany(mappedBy = "candidate",fetch = FetchType.LAZY)
//    private List<Criterias> criterias;
}
