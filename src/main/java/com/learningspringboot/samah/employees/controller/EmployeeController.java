package com.learningspringboot.samah.employees.controller;

import com.learningspringboot.samah.employees.model.Employee;
import com.learningspringboot.samah.employees.repository.EmployeeRepository;
import com.learningspringboot.samah.employees.service.EmployeeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Optional;

@RestController
public class EmployeeController {

    @Value("${spring.application.name}")
    private String name;
    @Value("${application.version}")
    private String version;
    @GetMapping("/info")
    public String getInfo(){
        return "This application is "+name+" and this version: "+version;
    }

    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private EmployeeRepository employeeRepository;

    @GetMapping("/employees")

    public ResponseEntity<List<Employee>> getAllEmployees(@RequestParam int pageNumber, @RequestParam int pageSize){
        return new ResponseEntity<List<Employee>>(employeeService.getAllEmployees(pageNumber, pageSize), HttpStatus.OK) ;
    }
    @GetMapping({"/allEmployees", "/" ,"/list"})
    public ModelAndView showEmployees(){
        ModelAndView modelAndView = new ModelAndView("list-employees");
        List<Employee> employees = employeeRepository.findAll();
        modelAndView.addObject("employees",employees);
        return modelAndView;
    }

    @GetMapping("/addEmployeeForm")
    public ModelAndView addEmployeeForm(){
        ModelAndView modelAndView = new ModelAndView("add-employee-form");
        Employee newEmployee = new Employee();
        modelAndView.addObject("employee",newEmployee);
        return modelAndView;
    }

    @PostMapping("/saveEmployee")
    public String saveEmployee(@ModelAttribute Employee employee){
        employeeRepository.save(employee);
        return "redirect:/list";
    }
    @GetMapping("/showUpdateForm")
    public ModelAndView showUpdateForm(@RequestParam Long employeeId){
        ModelAndView modelAndView = new ModelAndView("add-employee-form");
        Employee existingEmployee= employeeRepository.findById(employeeId).get();
        modelAndView.addObject("employee",existingEmployee);
        return modelAndView;
    }

    @GetMapping("/deleteEmployee")
    public String deleteEmployeeFromList(@RequestParam Long employeeId) {
        employeeRepository.deleteById(employeeId);
        return "redirect:/list";
    }

    @PostMapping("/employees")
    public ResponseEntity<Optional<Employee>> addEmployee(@Valid @RequestBody Employee employee){
        return new ResponseEntity<Optional<Employee>>(employeeService.addEmployee(employee), HttpStatus.CREATED);
    }

    @PutMapping("/employees")
    public ResponseEntity<Optional<Employee>> editEmployee(@Valid @RequestBody Employee employee, @RequestParam Long Id){
        employee.setId(Id);
        return new ResponseEntity<Optional<Employee>>(employeeService.editEmployee(employee), HttpStatus.OK);
    }

    @GetMapping("/employees/{Id}")
    public ResponseEntity<Optional<Optional<Employee>>> getEmployee(@PathVariable("Id") Long Id){
        return new ResponseEntity<Optional<Optional<Employee>>>(employeeService.getEmployee(Id), HttpStatus.OK);
    }

    @DeleteMapping("/employees")
    public ResponseEntity<HttpStatus> deleteEmployee(@RequestParam("Id") Long Id){
        employeeService.deleteEmployee(Id);
        return new ResponseEntity<HttpStatus>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/filterByDepartment/{departmentName}")
    public ResponseEntity<List<Employee>> getEmployeesByDepartmentName(@PathVariable("departmentName") String departmentName){
        return new ResponseEntity<List<Employee>>(employeeService.getEmployeesByDepartment(departmentName), HttpStatus.OK);
    }

    @GetMapping("/filterBySalary")
    public ResponseEntity<List<Employee>> getEmployeesBetween(@RequestParam double min, @RequestParam double max){
        return new ResponseEntity<List<Employee>>(employeeService.getEmployeesBySalaryBetween(min, max),HttpStatus.OK);
    }

    @GetMapping("/filterByName")
    public ResponseEntity<Employee> getEmployeesByName(@RequestParam String name){
        return new ResponseEntity<Employee>(employeeService.getEmployeesByName(name),HttpStatus.OK);
    }

    @GetMapping("/filterByJobTitle")
    public ResponseEntity<List<Employee>> getEmployeesByJobTitle(@RequestParam String jobTitle){
        return new ResponseEntity<List<Employee>>(employeeService.getEmployeesByJobTitle(jobTitle),HttpStatus.OK);
    }
    @GetMapping("/filterByNameAndDepartment")
    public ResponseEntity<List<Employee>> getEmployeesByNameAndDepartment(@RequestParam String name, @RequestParam String department){
        return new ResponseEntity<List<Employee>>(employeeService.getEmployeesByNameAndDepartment(name, department),HttpStatus.OK);
    }

    @GetMapping("/filterByNameContaining")
    public ResponseEntity<List<Employee>> getEmployeesByNameContaining(@RequestParam String keyword){
        return new ResponseEntity<List<Employee>>(employeeService.getEmployeesByNameContaining(keyword),HttpStatus.OK);
    }
}
