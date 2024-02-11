package com.kvscode.springboot.springrestcrud.service;

import java.util.List;

import com.kvscode.springboot.springrestcrud.entitymanager.Employee;


public interface EmployeeService {
    
    List<Employee> findAll();

    //getting employee by id
    Employee findById(int theId);

    //saving the employee
    Employee save(Employee theEmployee);

    //delete an employee by id
    void deleteById(int theId);
}
