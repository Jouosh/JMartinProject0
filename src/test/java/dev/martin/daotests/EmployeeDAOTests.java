package dev.martin.daotests;

import dev.martin.data.EmployeeDAO;
import dev.martin.data.EmployeeDAOLocal;
import dev.martin.entities.Employee;
import org.junit.jupiter.api.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class EmployeeDAOTests {

    static EmployeeDAO employeeDAO = new EmployeeDAOLocal();

    //Create and add an employee, then check if added
    @Test
    @Order(1)
    public void create_employee_test() {
        Employee employee = new Employee(0, "Jim Henson", 200_000);
        Employee createdEmployee = this.employeeDAO.createEmployee(employee);
        Assertions.assertNotEquals(0, createdEmployee.getId());
    }

    //Create a second employee, check if we get size 2 employees
    @Test
    @Order(2)
    public void get_all_employees_test() {
        Employee employee = new Employee(0, "Jimmy Lightning", 50);
        this.employeeDAO.createEmployee(employee);
        Assertions.assertEquals(2, this.employeeDAO.getAllEmployees().size());
    }

    //Find employee 1, check if correct employee
    @Test
    @Order(3)
    public void get_employee_by_id_test() {
        Employee employee = this.employeeDAO.getEmployeeById(1);
        Assertions.assertEquals("Jim Henson", employee.getName());
    }

    //Update employee 1, check if name updated
    @Test
    @Order(4)
    public void update_employee_test() {
        Employee employeeV2 = new Employee(1, "Jim Handsome", 3000000);
        employeeDAO.updateEmployee(employeeV2);
        Employee employee = employeeDAO.getEmployeeById(1);
        Assertions.assertEquals("Jim Handsome", employee.getName());
    }

    //delete employee 1, check if deleted
    @Test
    @Order(5)
    public void delete_employee_by_id_test() {
        boolean result = employeeDAO.deleteEmployee(1);
        Assertions.assertTrue(result);
    }

}
