package com.vheekey.crud.employee.controllers.v1;

import com.vheekey.crud.common.errors.NotFoundException;
import com.vheekey.crud.employee.enums.Teams;
import com.vheekey.crud.employee.models.Employee;
import com.vheekey.crud.employee.models.User;
import com.vheekey.crud.employee.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/v1/employees")
@Validated
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/teams")
    public List<Teams> getTeamsList() {
        return Arrays.stream(Teams.values()).toList();
    }

    @GetMapping
    public ResponseEntity<List<Employee>> getEmployees() {
        List<Employee> employees = this.employeeService.getEmployees();

        return ResponseEntity.ok(employees);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable int id) {
        return this.employeeService
                .getSingleEmployee(id)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new NotFoundException("Employee not found"));
    }

    @PostMapping
    public ResponseEntity<Employee> createEmployee(@RequestBody User user) {
        Employee employee = this.employeeService.createEmployee(user);

        return ResponseEntity.ok(employee);
    }

    @PatchMapping("/{id}/change-status")
    public ResponseEntity<Employee> changeActiveStatus(@PathVariable int id) {
        return ResponseEntity.ok(this.employeeService.changeEmployeeActiveStatus(id));
    }

    @PatchMapping("/{id}/update")
    public ResponseEntity<Employee> updateEmployee(@PathVariable int id, @RequestBody User user) {
        return ResponseEntity.ok(this.employeeService.updateEmployee(user, id));
    }
}
