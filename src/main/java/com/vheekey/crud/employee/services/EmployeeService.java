package com.vheekey.crud.employee.services;

import com.vheekey.crud.common.errors.NotFoundException;
import com.vheekey.crud.employee.entities.EmployeeEntity;
import com.vheekey.crud.employee.interfaces.EmployeeServiceInterface;
import com.vheekey.crud.employee.models.Employee;
import com.vheekey.crud.employee.models.User;
import com.vheekey.crud.employee.repositories.EmployeeRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EmployeeService implements EmployeeServiceInterface {

    private final PasswordService passwordService;
    private final EmployeeRepository employeeRepository;


    public EmployeeService(PasswordService passwordService,
                           EmployeeRepository employeeRepository) {
        this.passwordService = passwordService;
        this.employeeRepository = employeeRepository;
    }

    @Override
    public Employee createEmployee(User user) {
        EmployeeEntity employeeEntity = this.employeeRepository.save(toEmployeeEntity(user));

        return toEmployeeDTORetrieval(employeeEntity);
    }

    @Override
    public List<Employee> getEmployees() {
        return this.employeeRepository
                .findAll()
                .stream()
                .map(this::toEmployeeDTORetrieval)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Employee> getSingleEmployee(int id) {
        Optional<EmployeeEntity> employeeEntity = this.employeeRepository.findById(id);
        checkEmployeeExists(employeeEntity);

        return employeeEntity.map(this::toEmployeeDTORetrieval);
    }

    @Override
    public Employee updateEmployee(User user, int id) {
        Optional<EmployeeEntity> employeeEntity = this.employeeRepository.findById(id);
        checkEmployeeExists(employeeEntity);

        EmployeeEntity existingEmployeeEntity = employeeEntity.get();

        if (user.getEmail() != null) {
            existingEmployeeEntity.setEmail(user.getEmail());
        }

        if (user.getTeam() != null) {
            existingEmployeeEntity.setTeam(user.getTeam());
        }

        if (user.getName() != null) {
            existingEmployeeEntity.setName(user.getName());
        }

        if (user.isActive()) {
            existingEmployeeEntity.setActive(user.isActive());
        }

        if (user.getPassword() != null) {
            existingEmployeeEntity.setPassword(this.passwordService.encodePassword(user.getPassword()));
        }

        existingEmployeeEntity = this.employeeRepository.save(existingEmployeeEntity);

        return toEmployeeDTORetrieval(existingEmployeeEntity);
    }

    @Override
    public Employee changeEmployeeActiveStatus(int id) {
        Optional<EmployeeEntity> employeeEntity = this.employeeRepository.findById(id);
        checkEmployeeExists(employeeEntity);

        EmployeeEntity existingEmployeeEntity = employeeEntity.get();
        existingEmployeeEntity.setActive(!existingEmployeeEntity.isActive());
        existingEmployeeEntity = this.employeeRepository.save(existingEmployeeEntity);

        return toEmployeeDTORetrieval(existingEmployeeEntity);
    }

    private EmployeeEntity toEmployeeEntity(User user) {
        EmployeeEntity employeeEntity = new EmployeeEntity();
        employeeEntity.setEmail(user.getEmail());
        employeeEntity.setName(user.getName());
        employeeEntity.setTeam(user.getTeam());
        employeeEntity.setActive(false);
        employeeEntity.setPassword(this.passwordService.encodePassword(user.getPassword()));

        return employeeEntity;
    }

    private Employee toEmployeeDTORetrieval(EmployeeEntity employeeEntity) {
        Employee employee = new Employee();
        employee.setId(employeeEntity.getId());
        employee.setEmail(employeeEntity.getEmail());
        employee.setName(employeeEntity.getName());
        employee.setTeam(employeeEntity.getTeam());
        employee.setIsActive(employeeEntity.isActive());

        return employee;
    }

    private void checkEmployeeExists(Optional employeeEntity) {
        if (employeeEntity.isEmpty()) {
            throw new NotFoundException("Employee not found");
        }
    }
}
