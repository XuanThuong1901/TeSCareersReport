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
@Table(name = "RecruitmentStates")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class RecruitmentStates {
    @Id
    @Column(name = "RecruitmentStateId")
    private int recruitmentStateId;

    @Column(name = "RecruitmentStateName")
    private String recruitmentStateName;

//    @JsonIgnore
    @OneToMany(mappedBy = "recruitmentState")
    private List<Recruitments> recruitments;
}
