package com.tp.employeeconnect.service;

import com.tp.employeeconnect.dto.DepartmentRequest;
import com.tp.employeeconnect.dto.DepartmentResponse;
import com.tp.employeeconnect.entity.Department;
import com.tp.employeeconnect.repository.DepartmentRepository;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class DepartmentServiceImpl implements  DepartmentService{

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private DepartmentRepository departmentRepository;

    @Override
    public List<DepartmentResponse> getAllDepartments() {
        List<Department> list = departmentRepository.findAll();

        List<DepartmentResponse> departmentResponseList = new ArrayList<>();
        for(Department department: list){
            DepartmentResponse departmentResponse = new DepartmentResponse();
            departmentResponse.setId(department.getId());
            departmentResponse.setEmployee(department.getEmployee());
            departmentResponse.setName(department.getName());
            departmentResponse.setTotalSalary(departmentResponse.getTotalSalary());
            departmentResponseList.add(departmentResponse);
        }
        logger.info("Get All Departments Response :: {}",departmentResponseList);
        return departmentResponseList;
    }

    @Override
    public Department addDepartment(DepartmentRequest departmentRequest) {
        Department department = new Department();
        department.setName(departmentRequest.getName());
        departmentRepository.save(department);
        return department;
    }

    @Override
    public Boolean deleteDepartment(Long id) {
        Department department = departmentRepository.findById(id).orElse(null);
        if (department == null) {
            return false;
        }else if(department.getEmployee().isEmpty()) {
            departmentRepository.delete(department);
            return true;
        }
        return false;
    }

    @Override
    public DepartmentResponse getDepartmentById(Long id) {
        Department department = departmentRepository.findById(id).orElse(null);
        DepartmentResponse departmentResponse = new DepartmentResponse();
        if(department != null) {
            departmentResponse.setId(department.getId());
            departmentResponse.setEmployee(department.getEmployee());
            departmentResponse.setName(department.getName());
            departmentResponse.setTotalSalary(departmentResponse.getTotalSalary());
        }
        logger.info("Get Departments Response By ID :: {}", department);
        return departmentResponse;
    }
}
