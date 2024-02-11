package com.kvscode.springboot.springrestcrud.restcontroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kvscode.springboot.springrestcrud.entitymanager.Employee;
import com.kvscode.springboot.springrestcrud.service.EmployeeService;

@RestController
@RequestMapping("/api")
public class EmployeeRestController {
    private EmployeeService employeeService;

    @Autowired
    public EmployeeRestController(EmployeeService employeeService)
    {
        this.employeeService=employeeService;
    }

    @GetMapping("/employees")
    public List<Employee> findAll()
    {
        return employeeService.findAll();
    }

    @GetMapping("/employees/{employeeId}")
    public Employee getEmployee(@PathVariable int employeeId)
    {
        Employee theEmployee=employeeService.findById(employeeId);

        if(theEmployee==null)
        {
            throw new RuntimeException("Employee id not found "+ employeeId);
        }

        return theEmployee;
    }

    //add maping for post method for adding new employee
    @PostMapping("/employees")
    public Employee addEmployee(@RequestBody Employee theEmployee)
    {
        //in case they pass id in jason set id to 0
        //this is to force to save a new item instead of update 

        theEmployee.setId(0);
        Employee dbEmployee=employeeService.save(theEmployee);

        return dbEmployee;
    }

    @PutMapping("/employees")
    public Employee updatEmployee(@RequestBody Employee theEmployee)
    {
        Employee dbEmployee=employeeService.save(theEmployee);

        return dbEmployee;
    } 

    @DeleteMapping("/employees/{employeeId}")
    public String deleteById(@PathVariable int employeeId)
    {
        //get the employee needed to be deleted by using id 

        Employee theEmployee=employeeService.findById(employeeId);

        if(theEmployee==null)
        {
            throw new RuntimeException("The employee id is not found "+employeeId);
        }

        employeeService.deleteById(employeeId);

        return "Deleted employee with given id !";
    }
    
}
