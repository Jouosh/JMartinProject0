package dev.martin.data;

import dev.martin.entities.Employee;
import dev.martin.utils.ConnectionUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDAOPostgres implements EmployeeDAO{
    @Override
    public Employee createEmployee(Employee employee) {

        //Create connection within try-catch block to protect from SQLExceptions
        try (Connection conn = ConnectionUtil.createConnection()) {

            //Create sql base sql query and put into prepared statement
            String sql = "insert into employee values (default, ?, ?)";
            PreparedStatement preparedStatement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            //Push employee data in to fill in blanks
            preparedStatement.setString(1, employee.getName());
            preparedStatement.setInt(2, employee.getSalary());

            preparedStatement.execute();

            //Get generated id from result set, and set employee's id to match
            ResultSet keys = preparedStatement.getGeneratedKeys();
            keys.next();
            int generatedKey = keys.getInt("id");

            employee.setId(generatedKey);
            return employee;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;

    }

    @Override
    public List<Employee> getAllEmployees() {

        try (Connection conn = ConnectionUtil.createConnection()) {

            //Create select all query and put in prepared statement
            String sql = "select * from employee";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);

            //Execute query, and put result in resultSet
            ResultSet resultSet = preparedStatement.executeQuery();

            //Create list, fill with while loop until result set has been iterated through
            List<Employee> employeeList = new ArrayList();

            while (resultSet.next()) {
                Employee employee = new Employee();
                employee.setId(resultSet.getInt("id"));
                employee.setName(resultSet.getString("name"));
                employee.setSalary(resultSet.getInt("salary"));
                employeeList.add(employee);
            }
            //return final list
            return employeeList;


        } catch(SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public Employee getEmployeeById(int id) {

        try (Connection conn = ConnectionUtil.createConnection()) {

            //Create statement, and fill in id with id parameter
            String sql = "select * from employee where id = ?";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setInt(1, id);

            //Get result of query in result set, fill into an employee and return
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            Employee employee = new Employee();
            employee.setId(resultSet.getInt("id"));
            employee.setName(resultSet.getString("name"));
            employee.setSalary(resultSet.getInt("salary"));

            return employee;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public Employee updateEmployee(Employee employee) {

        try (Connection conn = ConnectionUtil.createConnection()) {

            //Create prepared statement for update, fill in blanks
            String sql = "update employee set name = ?, salary = ? where id = ?";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, employee.getName());
            preparedStatement.setInt(2, employee.getSalary());
            preparedStatement.setInt(3, employee.getId());

            //execute update and return employee
            preparedStatement.executeUpdate();
            return employee;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public Boolean deleteEmployee(int id) {

        try (Connection conn = ConnectionUtil.createConnection()) {

            //create prepared delete statement, set id to parameter id
            String sql = "delete from employee where id = ?";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setInt(1, id);

            //execute and return true
            preparedStatement.execute();
            return true;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }

    }
}
