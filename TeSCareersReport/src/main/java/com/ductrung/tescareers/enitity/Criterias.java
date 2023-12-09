package com.ductrung.tescareers.enitity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Criterias")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})

public class Criterias {
    @Id
    @Column(name = "CriteriaId")
    private int criteriaId;

    @Column(name = "CriteriaName")
    private String criteriaName;

    @Column(name = "IsDeleted")
    private boolean isDeleted;

    @JsonIgnore
    @OneToMany(mappedBy = "criteria",fetch = FetchType.LAZY)
    private List<RecruitmentCriterias> recruitmentCriterias;
}
