package com.tp.employeeconnect.dto;

import com.tp.employeeconnect.entity.Employee;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@Data
public class DepartmentResponse {
    private Long id;
    private String name;
    private double totalSalary;
    private List<Employee> employee;

    public double getTotalSalary() {
        return this.employee.stream().mapToDouble(Employee::getSalary).sum();
    }



}
