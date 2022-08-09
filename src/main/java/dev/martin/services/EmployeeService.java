package dev.martin.services;

import dev.martin.entities.Employee;

import java.util.List;

public interface EmployeeService {

    //Create
    public Employee registerEmployee(Employee employee);

    //Read
    public List<Employee> retrieveAllEmployees();
    public Employee retrieveEmployeeById(int id);

    //Update
    public Employee modifyEmployee(Employee employee);

    //Delete
    public boolean deleteEmployee(int id);

}
