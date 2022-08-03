package dev.martin.daotests;

import dev.martin.data.ExpenseDAO;
import dev.martin.data.ExpenseDAOLocal;
import dev.martin.entities.Expense;
import org.junit.jupiter.api.*;

import java.util.List;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ExpenseDAOTests {

    static ExpenseDAO expenseDAO = new ExpenseDAOLocal();

    @Test
    @Order(1)
    public void create_expense_test() {
        Expense expense = new Expense(0, 500, 2, "Yeahhhhh",
                Expense.Type.TRAVEL, Expense.Status.PENDING);
        expenseDAO.createExpense(expense);
        Assertions.assertNotEquals(0, expense.getId());
    }

    @Test
    @Order(2)
    public void get_all_expenses_test() {
        Expense expense = new Expense(0, 1000, 5, "NOOOOooooo",
                Expense.Type.SUPPLIES, Expense.Status.PENDING);
        expenseDAO.createExpense(expense);
        List<Expense> expenses = expenseDAO.getAllExpenses();
        Assertions.assertEquals(2, expenses.size());
    }

    @Test
    @Order(3)
    public void get_expense_by_id() {
        Expense expense = expenseDAO.getExpenseById(2);
        Assertions.assertEquals(1000, expense.getAmount());
    }

    @Test
    @Order(4)
    public void update_expense() {
        Expense expense = new Expense(2, 1005, 5, "nope",
                Expense.Type.TRAVEL, Expense.Status.APPROVED);
        expenseDAO.updateExpense(expense);
        Assertions.assertEquals(1005, expenseDAO.getExpenseById(2).getAmount());
    }

    @Test
    @Order(5)
    public void get_expense_by_status() {
        List<Expense> expenses = expenseDAO.getExpenseByStatus(Expense.Status.APPROVED);
        Assertions.assertEquals(1, expenses.size());
    }

}
