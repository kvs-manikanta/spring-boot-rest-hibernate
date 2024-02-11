package com.kvscode.springboot.springrestcrud.daomanager;

import java.util.List;

import com.kvscode.springboot.springrestcrud.entitymanager.Employee;

public interface EmployeeDAO {

    List<Employee> findAll();

    //getting employee by id
    Employee findById(int theId);

    //saving the employee
    Employee save(Employee theEmployee);

    //delete an employee by id
    void deleteById(int theId);

    
}
