package com.tp.employeeconnect.dto;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.tp.employeeconnect.entity.Department;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import java.time.LocalDate;
import lombok.Data;

@Data
public class EmployeeRequest {

    private Long id;

    private String name;

    private LocalDate dateOfBirth;

    private double salary;

    private long departmentId;
    private String departmentName;

}
