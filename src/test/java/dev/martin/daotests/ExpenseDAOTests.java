package dev.martin.daotests;

import dev.martin.data.ExpenseDAO;
import dev.martin.data.ExpenseDAOLocal;
import dev.martin.data.ExpenseDAOPostgres;
import dev.martin.entities.Expense;
import dev.martin.entities.Status;
import dev.martin.entities.Type;
import dev.martin.utils.ConnectionUtil;
import org.junit.jupiter.api.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ExpenseDAOTests {

    static ExpenseDAO expenseDAO = new ExpenseDAOPostgres();

    @BeforeAll
    static void setup() {
        try (Connection conn = ConnectionUtil.createConnection()) {

            String employeesql = "create table employee(" + "\n" +
                    "id serial primary key," + "\n" +
                    "name varchar(50)," + "\n" +
                    "salary int check (salary > 0)" + "\n" +
                    ");";

            String expensesql = "create table expense(" + "\n" +
                    "id serial primary key," + "\n" +
                    "amount int," + "\n" +
                    "issuer int references employee(id)," + "\n" +
                    "description varchar(200)," + "\n" +
                    "type varchar(20)," + "\n" +
                    "status varchar(10)" + "\n" +
                    ");";

            Statement statement =conn.createStatement();
            statement.execute(employeesql);
            statement.execute(expensesql);

            String sqlemployee1 = "insert into employee values (default, 'Jimbo Jonson', 50000);";
            String sqlemployee2 = "insert into employee values (default, 'Felix Knutsun', 2000);";

            statement.execute(sqlemployee1);
            statement.execute(sqlemployee2);

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Test
    @Order(1)
    public void create_expense_test() {
        Expense expense = new Expense(0, 500, 1, "Yeahhhhh",
                Type.TRAVEL, Status.PENDING);
        expenseDAO.createExpense(expense);
        Assertions.assertNotEquals(0, expense.getId());
    }

    @Test
    @Order(2)
    public void get_all_expenses_test() {
        Expense expense = new Expense(0, 1000, 2, "NOOOOooooo",
                Type.SUPPLIES, Status.PENDING);
        expenseDAO.createExpense(expense);
        List<Expense> expenses = expenseDAO.getAllExpenses();
        Assertions.assertEquals(2, expenses.size());
    }

    @Test
    @Order(3)
    public void get_expense_by_id_test() {
        Expense expense = expenseDAO.getExpenseById(2);
        Assertions.assertEquals(1000, expense.getAmount());
    }

    @Test
    @Order(4)
    public void get_expense_by_issuer_test() {
        List<Expense> expenses = expenseDAO.getExpenseByIssuer(2);
        Assertions.assertEquals(1, expenses.size());
    }

    @Test
    @Order(5)
    public void update_expense_test() {
        Expense expense = new Expense(2, 1005, 2, "nope",
                Type.TRAVEL, Status.APPROVED);
        expenseDAO.updateExpense(expense);
        Assertions.assertEquals(1005, expenseDAO.getExpenseById(2).getAmount());
    }

    @Test
    @Order(6)
    public void update_expense_status_test() {
        Expense expense = expenseDAO.updateExpenseStatus(1, Status.APPROVED);
        Assertions.assertEquals(Status.APPROVED, expenseDAO.getExpenseById(1).getStatus());
    }

    @Test
    @Order(7)
    public void get_expense_by_status_test() {
        List<Expense> expenses = expenseDAO.getExpenseByStatus(Status.APPROVED);
        Assertions.assertEquals(2, expenses.size());
    }

    @Test
    @Order(8)
    public void delete_expense_test() {
        boolean deleted = expenseDAO.deleteExpense(1);
        Assertions.assertTrue(deleted);
    }

    @AfterAll
    static void teardown() {
        try (Connection conn = ConnectionUtil.createConnection()) {
            String expensesql = "drop table expense";
            String employeesql = "drop table employee";
            Statement statement = conn.createStatement();
            statement.execute(expensesql);
            statement.execute(employeesql);

        } catch(SQLException e) {
            e.printStackTrace();
        }
    }

}
