package dev.martin.services;

import dev.martin.data.EmployeeDAO;
import dev.martin.entities.Employee;

import java.util.List;

public class EmployeeServiceImpl implements EmployeeService{

    //connection to data layer
    private EmployeeDAO employeeDAO;

    //constructor
    public EmployeeServiceImpl(EmployeeDAO employeeDAO) {
        this.employeeDAO = employeeDAO;
    }

    //Create
    @Override
    public Employee registerEmployee(Employee employee) {

        //Checking for valid name and salary before continuing
        if (employee.getName().length() == 0) {
            throw new RuntimeException("Employee's name must not be empty");
        }

        if (employee.getSalary() <= 0) {
            throw new RuntimeException("Employee's salary must be a positive nonzero value");
        }

        Employee savedEmployee = employeeDAO.createEmployee(employee);

        return savedEmployee;
    }

    //Read
    @Override
    public List<Employee> retrieveAllEmployees() {
        return employeeDAO.getAllEmployees();
    }

    @Override
    public Employee retrieveEmployeeById(int id) {
        return employeeDAO.getEmployeeById(id);
    }

    //Update
    @Override
    public Employee modifyEmployee(Employee employee) {

        //Checking for valid name and salary before continuing
        if (employee.getName().length() == 0) {
            throw new RuntimeException("Employee's name must not be empty");
        }

        if (employee.getSalary() <= 0) {
            throw new RuntimeException("Employee's salary must be a positive nonzero value");
        }

        return employeeDAO.updateEmployee(employee);
    }

    //Delete
    @Override
    public boolean deleteEmployee(int id) {
        boolean deleted = employeeDAO.deleteEmployee(id);
        return deleted;
    }
}
