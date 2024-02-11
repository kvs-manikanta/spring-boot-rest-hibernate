package com.kvscode.springboot.springrestcrud.daomanager;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.kvscode.springboot.springrestcrud.entitymanager.Employee;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

@Repository
public class EmployeeDAOJpaImpl implements EmployeeDAO{
    private EntityManager entityManager;

    public EmployeeDAOJpaImpl(EntityManager entityManager)
    {
        this.entityManager=entityManager;
    }

    @Override
    public List<Employee> findAll()
    {
        TypedQuery<Employee> theQuery=entityManager.createQuery("FROM Employee",Employee.class);

        return theQuery.getResultList();
    }

    @Override
    public Employee findById(int theId)
    {
        Employee theemployee=entityManager.find(Employee.class,theId);

        return theemployee;
    }

    //saving or updating the employee 
    @Override
    public Employee save(Employee theEmployee)
    {
        /* here merge check the id of an given employee if it equals to 0 
         * then insert it or save it else it updates it 
         */
        return entityManager.merge(theEmployee);
    }

    //delete an employee by id
    @Override
    public void deleteById(int theId)
    {
        Employee theEmployee=entityManager.find(Employee.class,theId);
        entityManager.remove(theEmployee);
    }
}
