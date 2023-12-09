package com.ductrung.tescareers.enitity.embeddable;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.*;

import java.io.Serializable;

@Setter
@Getter
@Embeddable
@NoArgsConstructor
@AllArgsConstructor
public class RecruitmentCriteriaId implements Serializable  {
    @Column(name = "RecruitmentId")
    private int recruitmentId;

    @Column(name = "CriteriaId")
    private int criteriaId;
}
