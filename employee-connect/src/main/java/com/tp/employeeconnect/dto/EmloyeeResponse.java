package com.tp.employeeconnect.dto;

import java.time.LocalDate;
import lombok.Data;

@Data
public class EmloyeeResponse {

    private Long id;

    private String name;

    private LocalDate dateOfBirth;

    private double salary;

    private Long departmentId;

    private String departmentName;

}
