package com.ductrung.tescareers.model.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ApplicationSubmissionRequest {
    private int recruitmentId;
    private int applicationStatus;
}
