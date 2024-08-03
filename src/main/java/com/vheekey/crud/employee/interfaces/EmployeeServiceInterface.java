package com.vheekey.crud.employee.interfaces;

import com.vheekey.crud.employee.models.Employee;
import com.vheekey.crud.employee.models.User;

import java.util.List;
import java.util.Optional;

public interface EmployeeServiceInterface {
    Employee createEmployee(User user);

    List<Employee> getEmployees();

    Optional<Employee> getSingleEmployee(int id);

    Employee updateEmployee(User user, int id);

    Employee changeEmployeeActiveStatus(int id);
}
