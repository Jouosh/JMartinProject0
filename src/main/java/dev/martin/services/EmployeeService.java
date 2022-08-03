package dev.martin.services;

import dev.martin.entities.Employee;

import java.util.List;

public interface EmployeeService {

    public Employee registerEmployee(Employee employee);

    public List<Employee> retrieveAllEmployees();
    public Employee retrieveEmployeeById(int id);

    public Employee modifyEmployee(Employee employee);

    public boolean deleteEmployee(int id);

}
