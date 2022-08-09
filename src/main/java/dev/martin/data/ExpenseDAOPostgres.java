package dev.martin.data;

import dev.martin.entities.Expense;
import dev.martin.entities.Status;
import dev.martin.entities.Type;
import dev.martin.utils.ConnectionUtil;

import java.awt.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ExpenseDAOPostgres implements ExpenseDAO {
    @Override
    public Expense createExpense(Expense expense) {

        try (Connection conn = ConnectionUtil.createConnection()) {

            //crete sql statement, and fill in blanks and execute
            String sql = "insert into expense values (default, ?, ?, ?, ?, ?)";
            PreparedStatement preparedStatement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setInt(1, expense.getAmount());
            preparedStatement.setInt(2, expense.getIssuer());
            preparedStatement.setString(3, expense.getDescription());
            preparedStatement.setString(4, expense.getType().toString());
            preparedStatement.setString(5, expense.getStatus().toString());

            preparedStatement.execute();

            //get generated id from result set and put in expense, then return
            ResultSet keys = preparedStatement.getGeneratedKeys();
            keys.next();
            int generatedKey = keys.getInt("id");

            expense.setId(generatedKey);
            return expense;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public List<Expense> getAllExpenses() {

        try (Connection conn = ConnectionUtil.createConnection()) {

            String sql = "select * from expense";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);

            ResultSet resultSet = preparedStatement.executeQuery();

            List<Expense> expenseList = new ArrayList();

            while (resultSet.next()) {
                Expense expense = new Expense();
                expense.setId(resultSet.getInt("id"));
                expense.setAmount(resultSet.getInt("amount"));
                expense.setIssuer(resultSet.getInt("issuer"));
                expense.setDescription(resultSet.getString("description"));
                expense.setType(Type.valueOf(resultSet.getString("type")));
                expense.setStatus(Status.valueOf(resultSet.getString("status")));
                expenseList.add(expense);
            }

            return expenseList;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public Expense getExpenseById(int id) {

        try (Connection conn = ConnectionUtil.createConnection()) {

            String sql = "select * from expense where id = ?";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setInt(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();

            Expense expense = new Expense();
            expense.setId(resultSet.getInt("id"));
            expense.setAmount(resultSet.getInt("amount"));
            expense.setIssuer(resultSet.getInt("issuer"));
            expense.setDescription(resultSet.getString("description"));
            expense.setType(Type.valueOf(resultSet.getString("type")));
            expense.setStatus(Status.valueOf(resultSet.getString("status")));

            return expense;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Expense> getExpenseByStatus(Status status) {

        try (Connection conn = ConnectionUtil.createConnection()) {

            String sql = "select * from expense where status = ?";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, status.toString());

            ResultSet resultSet = preparedStatement.executeQuery();

            List<Expense> expenseList = new ArrayList();

            while (resultSet.next()) {
                Expense expense = new Expense();
                expense.setId(resultSet.getInt("id"));
                expense.setAmount(resultSet.getInt("amount"));
                expense.setIssuer(resultSet.getInt("issuer"));
                expense.setDescription(resultSet.getString("description"));
                expense.setType(Type.valueOf(resultSet.getString("type")));
                expense.setStatus(Status.valueOf(resultSet.getString("status")));
                expenseList.add(expense);
            }

            return expenseList;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Expense> getExpenseByIssuer(int issuer) {

        try (Connection conn = ConnectionUtil.createConnection()) {

            String sql = "select * from expense where issuer = ?";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setInt(1, issuer);

            ResultSet resultSet = preparedStatement.executeQuery();

            List<Expense> expenseList = new ArrayList();

            while (resultSet.next()) {
                Expense expense = new Expense();
                expense.setId(resultSet.getInt("id"));
                expense.setAmount(resultSet.getInt("amount"));
                expense.setIssuer(resultSet.getInt("issuer"));
                expense.setDescription(resultSet.getString("description"));
                expense.setType(Type.valueOf(resultSet.getString("type")));
                expense.setStatus(Status.valueOf(resultSet.getString("status")));
                expenseList.add(expense);
            }

            return expenseList;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Expense updateExpense(Expense expense) {
        return null;
    }

    @Override
    public Expense updateExpenseStatus(int id, Status status) {
        return null;
    }

    @Override
    public boolean deleteExpense(int id) {
        return false;
    }
}
