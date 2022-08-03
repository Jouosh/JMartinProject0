package dev.martin.data;

import dev.martin.entities.Employee;

import java.util.List;

public interface EmployeeDAO {

    //Create
    Employee createEmployee(Employee employee);

    //Read
    List<Employee> getAllEmployees();
    Employee getEmployeeById(int id);

    //Update
    Employee updateEmployee(Employee employee);

    //Delete
    Boolean deleteEmployee(int id);

}
