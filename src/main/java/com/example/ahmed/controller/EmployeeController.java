package com.example.ahmed.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.ahmed.exception.ResourceNotFoundException;
import com.example.ahmed.model.Employee;
import com.example.ahmed.repository.EmployeeRepository;

@RestController
@CrossOrigin("*")
@RequestMapping("/employees")
public class EmployeeController {
  @Autowired
  EmployeeRepository employeeRepository;
  Logger logger = LoggerFactory.getLogger(EmployeeController.class);
  
  @PreAuthorize("hasRole('user')")
  @GetMapping("")
  public List<Employee> getAllEmployees(){
	   logger.info("in get all employees ");
	   return employeeRepository.findAll();
  }	
  /* @PostMapping(value="/users")
            ResponseEntity<?> create( @Valid @RequestBody User user) {
                
                User addeduser = userrepo.save(user);
                URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                                    .path("/{id}")
                                    .buildAndExpand(addeduser.getId())
                                    .toUri();
                
                return ResponseEntity.created(location).build();
            }*/
  @PreAuthorize("hasRole('user')")
  @PostMapping("/")
  public Employee saveEmployee( @RequestBody Employee employee) {
	  return employeeRepository.save(employee);
  }
  
  @PreAuthorize("hasRole('user')")
  @GetMapping("/{id}")
  public ResponseEntity<Employee> getEmployeeById(@PathVariable long id ){
	  Employee employee=employeeRepository.findById(id)
			  .orElseThrow(()-> new ResourceNotFoundException("employee not exists with id "+id ));
	  return ResponseEntity.ok(employee);
  }
  @PreAuthorize("hasRole('user')")
  @PutMapping("/{id}")
  public ResponseEntity<Employee> updateEmployee(@PathVariable  long id ,@Valid @RequestBody Employee employeeDetails){
	  Employee employee=employeeRepository.findById(id)
			  .orElseThrow(()-> new ResourceNotFoundException("employee not exists with id "+id ));
	  employee.setFirstName(employeeDetails.getFirstName());
	  employee.setLastName(employeeDetails.getLastName());
	  employee.setEmail(employeeDetails.getEmail());
	  
	  Employee updateEmployee=employeeRepository.save(employee);
	return ResponseEntity.ok(updateEmployee);   
}
@PreAuthorize("hasRole('user')")  
@DeleteMapping("/{id}")
public ResponseEntity<Employee> deleteEmployee(@PathVariable  long id){
	Employee employee=employeeRepository.findById(id)
			.orElseThrow( () -> new ResourceNotFoundException("employee with that id not found") );

		employeeRepository.delete(employee);
	
	return  ResponseEntity.ok( employee);
}


  }