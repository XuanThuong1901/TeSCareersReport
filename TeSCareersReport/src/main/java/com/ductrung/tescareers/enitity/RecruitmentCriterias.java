package com.ductrung.tescareers.enitity;

import com.ductrung.tescareers.enitity.embeddable.RecruitmentCriteriaId;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import java.io.Serializable;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "RecruitmentCriterias")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class RecruitmentCriterias {

    @EmbeddedId
    private RecruitmentCriteriaId recruitmentCriteriaId;

    @JsonIgnoreProperties("recruitmentCriterias")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "RecruitmentId", referencedColumnName = "recruitmentId", insertable = false, updatable = false)
    private Recruitments recruitment;

    @JsonIgnoreProperties("recruitmentCriterias")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "CriteriaId", referencedColumnName = "criteriaId", insertable = false, updatable = false)
    private Criterias criteria;

}
