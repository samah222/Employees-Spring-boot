package com.learningspringboot.samah.employees.service;

import com.learningspringboot.samah.employees.model.Employee;
import com.learningspringboot.samah.employees.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService{

    @Autowired
    private EmployeeRepository empRepo;
    @Override
    public List<Employee> getAllEmployees(int pageNumber, int pageSize) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        return empRepo.findAll(pageable).getContent();
    }

    @Override
    public Optional<Employee> addEmployee(Employee employee) {
        return Optional.of(empRepo.save(employee));
    }

    @Override
    public Optional<Employee> editEmployee(Employee employee) {
        return Optional.of(empRepo.save(employee));
    }

    @Override
    public Optional<Optional<Employee>> getEmployee(Long id) {
        return Optional.of(empRepo.findById(id));
//        return employee
//        if (employee.isPresent())
//            return employee.get();
//        throw new RuntimeException("This employee is not present");
    }

    @Override
    public void deleteEmployee(Long Id) {
        empRepo.deleteById(Id);
    }

    @Override
    public List<Employee> getEmployeesByDepartment(String department) {
        return empRepo.findByDepartment(department);
    }

    @Override
    public List<Employee> getEmployeesBySalaryBetween(double min, double max) {
        return empRepo.findBySalaryBetween(min,max);
    }

    @Override
    public List<Employee> getEmployeesByNameAndDepartment(String name, String department) {
        return empRepo.findByNameAndDepartment(name, department);
    }

    @Override
    public List<Employee> getEmployeesByNameContaining(String keyword) {
        return empRepo.findByNameContaining(keyword);
    }

    @Override
    public Employee getEmployeesByName(String name) {
        return empRepo.findByName(name);
    }

    @Override
    public List<Employee> getEmployeesByJobTitle(String jobTitle) {
        return empRepo.findByJobTitle(jobTitle);
    }


}
