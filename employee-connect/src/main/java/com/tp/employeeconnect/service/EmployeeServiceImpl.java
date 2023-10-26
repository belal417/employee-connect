package com.tp.employeeconnect.service;

import com.tp.employeeconnect.dto.EmloyeeResponse;
import com.tp.employeeconnect.dto.EmployeeRequest;
import com.tp.employeeconnect.entity.Department;
import com.tp.employeeconnect.entity.Employee;
import com.tp.employeeconnect.repository.DepartmentRepository;
import com.tp.employeeconnect.repository.EmployeeRepository;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    EmployeeRepository employeeRepository;
    @Autowired
    private DepartmentRepository departmentRepository;

    @Override
    public EmloyeeResponse getDepartmentById(Long id) {
        Employee employee = employeeRepository.findById(id).orElse(null);
        EmloyeeResponse emloyeeResponse = new EmloyeeResponse();
        if (employee != null) {
            emloyeeResponse.setDepartmentId(employee.getDepartment().getId());
            emloyeeResponse.setId(employee.getId());
            emloyeeResponse.setDepartmentName(employee.getDepartment().getName());
            emloyeeResponse.setSalary(employee.getSalary());
            emloyeeResponse.setDateOfBirth(employee.getDateOfBirth());
            emloyeeResponse.setName(employee.getName());
        }
        return emloyeeResponse;
    }

    @Override
    public boolean deleteEmployee(Long id) {
        employeeRepository.deleteById(id);
        return true;
    }

    @Override
    public Employee addEmployee(EmployeeRequest employeeRequest) {
        Employee employee = new Employee();
        employee.setName(employeeRequest.getName());
        employee.setSalary(employeeRequest.getSalary());
        employee.setDateOfBirth(employeeRequest.getDateOfBirth());
        Optional<Department> departmentById = departmentRepository.findById(employeeRequest.getDepartmentId());
        if (departmentById.isPresent()) {
            employee.setDepartment(departmentById.get());
        } else {
            Optional<Department> departmentbyName = departmentRepository.findByName(employeeRequest.getName());
            if (departmentbyName.isPresent()) {
                employee.setDepartment(departmentbyName.get());
            } else {
                Department newDepartment = new Department();
                newDepartment.setName(employeeRequest.getName());
            }
        }
        employeeRepository.save(employee);
        return employee;
    }

    @Override
    public Employee updateEmployee(EmployeeRequest employeeRequest) {
        Employee existingEmployee = employeeRepository.findById(employeeRequest.getId()).orElse(null);
        if (existingEmployee != null) {
            // Check if the ID is already set, if not, create a new employee with the same ID
            if (existingEmployee.getId() != null && existingEmployee.getId() != 0) {
                existingEmployee.setName(employeeRequest.getName());
                Optional<Department> departmentById = departmentRepository.findById(employeeRequest.getDepartmentId());
                existingEmployee.setDepartment(departmentById.get());
                existingEmployee.setSalary(employeeRequest.getSalary());
                existingEmployee.setDateOfBirth(employeeRequest.getDateOfBirth());
                return employeeRepository.save(existingEmployee);
            }
        }
        return existingEmployee;
    }
}
