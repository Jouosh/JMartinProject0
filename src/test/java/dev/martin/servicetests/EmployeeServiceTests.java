package dev.martin.servicetests;

import dev.martin.data.EmployeeDAO;
import dev.martin.entities.Employee;
import dev.martin.services.EmployeeService;
import dev.martin.services.EmployeeServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class EmployeeServiceTests {

    static EmployeeDAO employeeDAO = Mockito.mock(EmployeeDAO.class);
    static EmployeeService employeeService = new EmployeeServiceImpl(employeeDAO);

    @Test
    void registered_employee_must_have_a_name() {
        Employee employee = new Employee(1, "", 50000);
        Mockito.when(employeeDAO.createEmployee(employee)).thenReturn(employee);
        Assertions.assertThrows(RuntimeException.class, () -> {
            employeeService.registerEmployee(employee);
        });
    }

    @Test
    void registered_employee_must_have_a_positive_salary() {
        Employee employee = new Employee(1, "Jimmy Lightning", -10);
        Mockito.when(employeeDAO.createEmployee(employee)).thenReturn(employee);
        Assertions.assertThrows(RuntimeException.class, () -> {
            employeeService.registerEmployee(employee);
        });
    }

    @Test
    void modified_employee_must_have_a_name() {
        Employee employee = new Employee(1, "", 50000);
        Mockito.when(employeeDAO.updateEmployee(employee)).thenReturn(employee);
        Assertions.assertThrows(RuntimeException.class, () -> {
            employeeService.registerEmployee(employee);
        });
    }

    @Test
    void modified_employee_must_have_a_positive_salary() {
        Employee employee = new Employee(1, "Jimmy Lightning", -50);
        Mockito.when(employeeDAO.updateEmployee(employee)).thenReturn(employee);
        Assertions.assertThrows(RuntimeException.class, () -> {
            employeeService.registerEmployee(employee);
        });
    }

}
