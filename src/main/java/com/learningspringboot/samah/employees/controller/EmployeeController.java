package com.learningspringboot.samah.employees.controller;

import com.learningspringboot.samah.employees.model.Employee;
import com.learningspringboot.samah.employees.repository.EmployeeRepository;
import com.learningspringboot.samah.employees.service.EmployeeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Optional;
@Tag(name = "Employees APIs", description = "All Employees project APIs")

@RestController
public class EmployeeController {
    @Value("${spring.application.name}")
    private String name;
    @Value("${application.version}")
    private String version;

    @Operation(summary = "Get all application info", description = "Get all application info")
    @ApiResponses(value = {@ApiResponse(responseCode = "200")})
    @GetMapping("/info")
    public String getInfo(){
        return "This application is "+name+" and this version: "+version;
    }

    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private EmployeeRepository employeeRepository;

    @Operation(summary = "Get pages for all employees ", description = "Get pages for all employees")
    @ApiResponses(value = {@ApiResponse(responseCode = "200")})
    @GetMapping("/employees")
    public ResponseEntity<List<Employee>> getAllEmployees(@RequestParam int pageNumber, @RequestParam int pageSize){
        return new ResponseEntity<List<Employee>>(employeeService.getAllEmployees(pageNumber, pageSize), HttpStatus.OK) ;
    }
    @Operation(summary = "Get all employees ", description = "Get all employees")
    @ApiResponses(value = {@ApiResponse(responseCode = "200")})
    @GetMapping({"/allEmployees", "/" ,"/list"})
    public ModelAndView showEmployees(){
        ModelAndView modelAndView = new ModelAndView("list-employees");
        List<Employee> employees = employeeRepository.findAll();
        modelAndView.addObject("employees",employees);
        return modelAndView;
    }
    @Operation(summary = "Add employee in form ", description = "Add employee in form")
    //@ApiResponses(value = {@ApiResponse(responseCode = "200")})
    @GetMapping("/addEmployeeForm")
    public ModelAndView addEmployeeForm(){
        ModelAndView modelAndView = new ModelAndView("add-employee-form");
        Employee newEmployee = new Employee();
        modelAndView.addObject("employee",newEmployee);
        return modelAndView;
    }

    @Operation(summary = "Save employee in form ", description = "Save employee in form")
    @ApiResponses(value = {@ApiResponse(responseCode = "200")})
    @PostMapping("/saveEmployee")
    public String saveEmployee(@ModelAttribute Employee employee){
        employeeRepository.save(employee);
        return "redirect:/list";
    }
    @Operation(summary = "Show update form ", description = "Show update form")
    @ApiResponses(value = {@ApiResponse(responseCode = "200")})
    @GetMapping("/showUpdateForm")
    public ModelAndView showUpdateForm(@RequestParam Long employeeId){
        ModelAndView modelAndView = new ModelAndView("add-employee-form");
        Employee existingEmployee= employeeRepository.findById(employeeId).get();
        modelAndView.addObject("employee",existingEmployee);
        return modelAndView;
    }

    @Operation(summary = "Delete employee by form ", description = "Delete employee by form")
    @ApiResponses(value = {@ApiResponse(responseCode = "200")})
    @GetMapping("/deleteEmployee")
    public String deleteEmployeeFromList(@RequestParam Long employeeId) {
        employeeRepository.deleteById(employeeId);
        return "redirect:/list";
    }

    @Operation(summary = "Add an employee ", description = "Add an employee")
    @ApiResponses(value = {@ApiResponse(responseCode = "201")})
    @PostMapping("/employees")
    public ResponseEntity<Optional<Employee>> addEmployee(@Valid @RequestBody Employee employee){
        return new ResponseEntity<Optional<Employee>>(employeeService.addEmployee(employee), HttpStatus.CREATED);
    }

    @Operation(summary = "Edit an employee ", description = "Edit an employee")
    @ApiResponses(value = {@ApiResponse(responseCode = "200")})
    @PutMapping("/employees")
    public ResponseEntity<Optional<Employee>> editEmployee(@Valid @RequestBody Employee employee, @RequestParam Long Id){
        employee.setId(Id);
        return new ResponseEntity<Optional<Employee>>(employeeService.editEmployee(employee), HttpStatus.OK);
    }

    @Operation(summary = "Get an employee ", description = "Edit an employee")
    @ApiResponses(value = {@ApiResponse(responseCode = "200")})
    @GetMapping("/employees/{Id}")
    public ResponseEntity<Optional<Optional<Employee>>> getEmployee(@PathVariable("Id") Long Id){
        return new ResponseEntity<Optional<Optional<Employee>>>(employeeService.getEmployee(Id), HttpStatus.OK);
    }

    @Operation(summary = "Delete an employee ", description = "Delete an employee")
    @ApiResponses(value = {@ApiResponse(responseCode = "204")})
    @DeleteMapping("/employees")
    public ResponseEntity<HttpStatus> deleteEmployee(@RequestParam("Id") Long Id){
        employeeService.deleteEmployee(Id);
        return new ResponseEntity<HttpStatus>(HttpStatus.NO_CONTENT);
    }

    @Operation(summary = "Get employees by department ", description = "Get employees by department")
    @ApiResponses(value = {@ApiResponse(responseCode = "200")})
    @GetMapping("/filterByDepartment/{departmentName}")
    public ResponseEntity<List<Employee>> getEmployeesByDepartmentName(@PathVariable("departmentName") String departmentName){
        return new ResponseEntity<List<Employee>>(employeeService.getEmployeesByDepartment(departmentName), HttpStatus.OK);
    }

    @Operation(summary = "Get employees by salary ", description = "Get employees by salary")
    @ApiResponses(value = {@ApiResponse(responseCode = "200")})
    @GetMapping("/filterBySalary")
    public ResponseEntity<List<Employee>> getEmployeesBetween(@RequestParam double min, @RequestParam double max){
        return new ResponseEntity<List<Employee>>(employeeService.getEmployeesBySalaryBetween(min, max),HttpStatus.OK);
    }

    @Operation(summary = "Get employees by name ", description = "Get employees by name")
    @ApiResponses(value = {@ApiResponse(responseCode = "200")})
    @GetMapping("/filterByName")
    public ResponseEntity<Employee> getEmployeesByName(@RequestParam String name){
        return new ResponseEntity<Employee>(employeeService.getEmployeesByName(name),HttpStatus.OK);
    }

    @Operation(summary = "Get employees by JobTitle ", description = "Get employees by JobTitle")
    @ApiResponses(value = {@ApiResponse(responseCode = "200")})
    @GetMapping("/filterByJobTitle")
    public ResponseEntity<List<Employee>> getEmployeesByJobTitle(@RequestParam String jobTitle){
        return new ResponseEntity<List<Employee>>(employeeService.getEmployeesByJobTitle(jobTitle),HttpStatus.OK);
    }

    @Operation(summary = "Get employees by name and department", description = "Get employees by name and department")
    @ApiResponses(value = {@ApiResponse(responseCode = "200")})
    @GetMapping("/filterByNameAndDepartment")
    public ResponseEntity<List<Employee>> getEmployeesByNameAndDepartment(@RequestParam String name, @RequestParam String department){
        return new ResponseEntity<List<Employee>>(employeeService.getEmployeesByNameAndDepartment(name, department),HttpStatus.OK);
    }

    @Operation(summary = "Get employees by name keyword", description = "Get employees by name containing keyword")
    @ApiResponses(value = {@ApiResponse(responseCode = "200")})
    @GetMapping("/filterByNameContaining")
    public ResponseEntity<List<Employee>> getEmployeesByNameContaining(@RequestParam String keyword){
        return new ResponseEntity<List<Employee>>(employeeService.getEmployeesByNameContaining(keyword),HttpStatus.OK);
    }
}
