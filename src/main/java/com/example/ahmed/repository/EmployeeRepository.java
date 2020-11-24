package com.example.ahmed.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.ahmed.model.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee,Long> {

}
