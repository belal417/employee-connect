package com.tp.employeeconnect.service;

import com.tp.employeeconnect.dto.DepartmentRequest;
import com.tp.employeeconnect.dto.DepartmentResponse;
import com.tp.employeeconnect.entity.Department;
import java.util.List;

public interface DepartmentService {

    List<DepartmentResponse> getAllDepartments();

    Department addDepartment(DepartmentRequest departmentRequest);

    Boolean deleteDepartment(Long id);

    DepartmentResponse getDepartmentById(Long id);
}
