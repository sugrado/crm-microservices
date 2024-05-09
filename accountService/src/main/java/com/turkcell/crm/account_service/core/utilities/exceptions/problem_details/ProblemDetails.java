package com.turkcell.crm.account_service.core.utilities.exceptions.problem_details;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class ProblemDetails {
    //rfce standarts
    private String title;
    private String detail;
    private String status;
    private String type;
}