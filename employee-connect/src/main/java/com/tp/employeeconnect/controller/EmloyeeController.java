package com.tp.employeeconnect.controller;


import com.tp.employeeconnect.dto.DepartmentRequest;
import com.tp.employeeconnect.dto.DepartmentResponse;
import com.tp.employeeconnect.dto.EmloyeeResponse;
import com.tp.employeeconnect.dto.EmployeeRequest;
import com.tp.employeeconnect.entity.Department;
import com.tp.employeeconnect.entity.Employee;
import com.tp.employeeconnect.service.EmployeeService;
import java.util.Map;
import java.util.Optional;
import lombok.extern.java.Log;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/employees")
public class EmloyeeController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private EmployeeService employeeService;
    @GetMapping("/{id}")
    public ResponseEntity<EmloyeeResponse> getEmployeeById(@PathVariable Long id){

        EmloyeeResponse employee = employeeService.getDepartmentById(id);
        if (employee==null) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(employee);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteEmployeeById(@PathVariable Long id){
        if (employeeService.deleteEmployee(id))
            return new ResponseEntity(Map.of("message","Successfully deleted Employee with Id: "+id), HttpStatus.OK) ;
        return new ResponseEntity(Map.of("message","Failed to delete Employee wiyh Id: "+id), HttpStatus.NOT_ACCEPTABLE) ;
    }


    @PostMapping
    public ResponseEntity addEmployee(@RequestBody EmployeeRequest employeeRequest){
        Optional<Employee> employee = Optional.ofNullable(employeeService.addEmployee(employeeRequest));
        if (employee.isEmpty())
            return ResponseEntity.badRequest().build();
        return new ResponseEntity(Map.of("message","Successfully Created Employee '"+employee.get().getName()+ "' with id: "+employee.get().getId()), HttpStatus.CREATED) ;
    }

    @PutMapping
    public ResponseEntity updateEmployee(@RequestBody EmployeeRequest employeeRequest){
        Optional<Employee> employee = Optional.ofNullable(employeeService.updateEmployee(employeeRequest));
        if (employee.isEmpty())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        return ResponseEntity.ok(employee) ;
    }
}
