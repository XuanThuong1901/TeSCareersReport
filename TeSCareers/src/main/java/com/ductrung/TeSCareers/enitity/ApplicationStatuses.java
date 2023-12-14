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
@Table(name = "ApplicationStatuses")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})

public class ApplicationStatuses {
    @Id
    @Column(name = "ApplicationStatusId")
    private Integer applicationStatusId;

    @Column(name = "ApplicationStatusName")
    private String applicationStatusName;

    @JsonIgnore
    @OneToMany(mappedBy = "applicationStatus", fetch = FetchType.LAZY)
    private List<ApplicationHistories> applicationHistories;

    @JsonIgnore
    @OneToMany(mappedBy = "applicationStatus", fetch = FetchType.LAZY)
    private List<ApplicationSubmissions> applicationSubmissions;

}
