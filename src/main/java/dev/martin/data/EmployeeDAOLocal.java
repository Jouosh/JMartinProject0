package dev.martin.data;

import dev.martin.entities.Employee;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EmployeeDAOLocal implements EmployeeDAO {

    //private members for local storage
    private Map<Integer, Employee> employeeTable = new HashMap();
    private int idMaker = 1;

    //Create
    @Override
    public Employee createEmployee(Employee employee) {
        employee.setId(idMaker);
        idMaker++;
        employeeTable.put(employee.getId(), employee);
        return employee;
    }

    //Read
    @Override
    public List<Employee> getAllEmployees() {

        List<Employee> employeeList = new ArrayList();
        employeeList.addAll(employeeTable.values());

        return employeeList;
    }

    @Override
    public Employee getEmployeeById(int id) {
        return employeeTable.get(id);
    }

    //Update
    @Override
    public Employee updateEmployee(Employee employee) {
        employeeTable.put(employee.getId(), employee);
        return employee;
    }

    //Delete
    @Override
    public Boolean deleteEmployee(int id) {

        Employee employee = employeeTable.remove(id);

        //return false if there was no employee to remove
        if (employee == null) {
            return false;
        }
        return true;

    }


}
