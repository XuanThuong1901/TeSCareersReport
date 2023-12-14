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
@Table(name = "Departments")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})

public class Departments {

    @Id
    @Column(name = "DepartmentId")
    private Integer departmentId;

    @Column(name = "DepartmentName")
    private String departmentName;

    @Column(name = "Description")
    private String description;

    @Column(name = "IsDeleted")
    private boolean isDeleted;

    @JsonIgnore
    @OneToMany(mappedBy = "department",fetch = FetchType.LAZY)
    private List<Recruitments> recruitments;
}
