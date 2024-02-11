package com.kvscode.springboot.springrestcrud.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kvscode.springboot.springrestcrud.daomanager.EmployeeDAO;
import com.kvscode.springboot.springrestcrud.entitymanager.Employee;

import jakarta.transaction.Transactional;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    
    private EmployeeDAO employeeDAO;

    @Autowired
    public EmployeeServiceImpl(EmployeeDAO employeeDAO)
    {
        this.employeeDAO=employeeDAO;
    }

    @Override
    public List<Employee> findAll()
    {
        return employeeDAO.findAll();
    }

    //delete an employee by id
    /*
     * delegating the calls fro, service to Employee DAo
     */

    @Transactional
    @Override
    public Employee findById(int theId)
    {
        return employeeDAO.findById(theId);
    }

    //delete an employee by id
    /*
     * delegating the calls fro, service to Employee DAo
     */
    
    //saving the employee
    @Override
    @Transactional
    public Employee save(Employee theEmployee)
    {
        return employeeDAO.save(theEmployee);
    }

    //delete an employee by id
    /*
     * delegating the calls fro, service to Employee DAo
     */
    @Transactional
    @Override
    public void deleteById(int theId)
    {
        employeeDAO.deleteById(theId);
    }
}
