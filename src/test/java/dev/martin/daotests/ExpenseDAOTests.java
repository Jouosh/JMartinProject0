package dev.martin.daotests;

import dev.martin.data.ExpenseDAO;
import dev.martin.data.ExpenseDAOLocal;
import dev.martin.entities.Expense;
import dev.martin.entities.Status;
import dev.martin.entities.Type;
import org.junit.jupiter.api.*;

import java.util.List;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ExpenseDAOTests {

    static ExpenseDAO expenseDAO = new ExpenseDAOLocal();

    @Test
    @Order(1)
    public void create_expense_test() {
        Expense expense = new Expense(0, 500, 2, "Yeahhhhh",
                Type.TRAVEL, Status.PENDING);
        expenseDAO.createExpense(expense);
        Assertions.assertNotEquals(0, expense.getId());
    }

    @Test
    @Order(2)
    public void get_all_expenses_test() {
        Expense expense = new Expense(0, 1000, 5, "NOOOOooooo",
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
        List<Expense> expenses = expenseDAO.getExpenseByIssuer(5);
        Assertions.assertEquals(1, expenses.size());
    }

    @Test
    @Order(5)
    public void update_expense_test() {
        Expense expense = new Expense(2, 1005, 5, "nope",
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

}
