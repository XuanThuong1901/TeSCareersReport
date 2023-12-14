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
@Table(name = "ApplicationHistories")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})

public class ApplicationHistories {
    @Id
    @Column(name = "ApplicationHistoryId")
    private Integer applicationHistoryId;

    @Column(name = "DateTime")
    private Date dateTime;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "ApplicationSubmissionId")
    private ApplicationSubmissions applicationSubmission;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "ApplicationStatusId")
    @JsonIgnoreProperties("applicationHistories")
    private ApplicationStatuses applicationStatus;
}
