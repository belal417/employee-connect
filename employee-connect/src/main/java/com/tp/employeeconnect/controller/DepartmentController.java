package com.tp.employeeconnect.controller;


import com.tp.employeeconnect.dto.DepartmentRequest;
import com.tp.employeeconnect.dto.DepartmentResponse;
import com.tp.employeeconnect.entity.Department;
import com.tp.employeeconnect.service.DepartmentService;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/v1/deparments")
public class DepartmentController {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private DepartmentService departmentService;

    @GetMapping
    public ResponseEntity<List<DepartmentResponse>> getAllDepartments() {
        List<DepartmentResponse> departmentList = departmentService.getAllDepartments();
        if (departmentList.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(departmentList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DepartmentResponse> getDepartmentById(@PathVariable Long id) {
        DepartmentResponse department = departmentService.getDepartmentById(id);
        if (department==null) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(department);
    }

    @PostMapping
    public ResponseEntity addDepartment(@RequestBody DepartmentRequest departmentRequest){
        logger.info("Department :: Create Department Request :: {} ",departmentRequest);
        Optional<Department> department = Optional.ofNullable(departmentService.addDepartment(departmentRequest));
        if (department.isEmpty())
            return ResponseEntity.badRequest().build();
        return new ResponseEntity(Map.of("message","Successfully Created Department '"+department.get().getName()+ "' with id: "+department.get().getId()), HttpStatus.CREATED) ;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteDepartment(@PathVariable Long id){
        logger.info("Department :: Delete Department Request :: Departement ID :: {} ",id);
        if (departmentService.deleteDepartment(id))
            return new ResponseEntity(Map.of("message","Successfully deleted Department Id: "+id), HttpStatus.OK) ;
        return new ResponseEntity(Map.of("message","Failed to delete Department Id: "+id), HttpStatus.NOT_ACCEPTABLE) ;
    }

}
