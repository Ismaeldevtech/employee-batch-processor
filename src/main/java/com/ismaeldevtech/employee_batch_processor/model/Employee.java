package com.ismaeldevtech.employee_batch_processor.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Employee {
    private String firstName;
    private String lastName;
    private String email;
    private String department;
    private Double salary;
}