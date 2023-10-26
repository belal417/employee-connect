package com.tp.employeeconnect.service;

import com.tp.employeeconnect.dto.EmloyeeResponse;
import com.tp.employeeconnect.dto.EmployeeRequest;
import com.tp.employeeconnect.entity.Employee;

public interface EmployeeService {

    EmloyeeResponse getDepartmentById(Long id);

    boolean deleteEmployee(Long id);


    Employee addEmployee(EmployeeRequest employeeRequest);

    Employee updateEmployee(EmployeeRequest employeeRequest);
}
