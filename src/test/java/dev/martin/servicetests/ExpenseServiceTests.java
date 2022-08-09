package dev.martin.servicetests;

import dev.martin.data.ExpenseDAO;
import dev.martin.entities.Expense;
import dev.martin.entities.Status;
import dev.martin.entities.Type;
import dev.martin.services.ExpenseService;
import dev.martin.services.ExpenseServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class ExpenseServiceTests {

    static ExpenseDAO expenseDAO = Mockito.mock(ExpenseDAO.class);
    static ExpenseService expenseService = new ExpenseServiceImpl(expenseDAO);

    @Test
    public void register_expense_must_have_a_valid_issuer() {
        Expense expense = new Expense(1, 200, -1, "Good Food Yummy Yummy", Type.FOOD, Status.PENDING);
        Mockito.when(expenseDAO.createExpense(expense)).thenReturn(expense);
        Assertions.assertThrows(RuntimeException.class, () -> {
           expenseService.registerExpense(expense);
        });
    }

    @Test
    public void register_expense_must_have_a_positive_amount() {
        Expense expense = new Expense(1, -20, 1, "Good Food Yummy Yummy", Type.FOOD, Status.PENDING);
        Mockito.when(expenseDAO.createExpense(expense)).thenReturn(expense);
        Assertions.assertThrows(RuntimeException.class, () -> {
            expenseService.registerExpense(expense);
        });
    }

    @Test
    public void modify_expense_previous_must_not_be_pending() {
        Expense oldExpense = new Expense(1, 200, 1,"Good Food Yummy Yummy", Type.FOOD, Status.APPROVED);
        Expense newExpense = new Expense(1, 200, 1,"Good Food Yummy Yummy", Type.FOOD, Status.PENDING);
        Mockito.when(expenseDAO.updateExpense(newExpense)).thenReturn(newExpense);
        Mockito.when(expenseDAO.getExpenseById(1)).thenReturn(oldExpense);
        Assertions.assertThrows(RuntimeException.class, () -> {
           expenseService.modifyExpense(newExpense);
        });
    }

    @Test
    public void modify_expense_must_have_a_valid_issuer() {
        Expense oldExpense = new Expense(1, 200, 1,"Good Food Yummy Yummy", Type.FOOD, Status.PENDING);
        Expense newExpense = new Expense(1, 200, -1,"Good Food Yummy Yummy", Type.FOOD, Status.PENDING);
        Mockito.when(expenseDAO.updateExpense(newExpense)).thenReturn(newExpense);
        Mockito.when(expenseDAO.getExpenseById(1)).thenReturn(oldExpense);
        Assertions.assertThrows(RuntimeException.class, () -> {
            expenseService.modifyExpense(newExpense);
        });
    }

    @Test
    public void modify_expense_must_have_a_positive_amount() {
        Expense oldExpense = new Expense(1, 200, 1,"Good Food Yummy Yummy", Type.FOOD, Status.PENDING);
        Expense newExpense = new Expense(1, -20, 1,"Good Food Yummy Yummy", Type.FOOD, Status.PENDING);
        Mockito.when(expenseDAO.updateExpense(newExpense)).thenReturn(newExpense);
        Mockito.when(expenseDAO.getExpenseById(1)).thenReturn(oldExpense);
        Assertions.assertThrows(RuntimeException.class, () -> {
            expenseService.modifyExpense(newExpense);
        });
    }

    @Test
    public void modify_expense_status_must_be_pending() {
        Expense expense = new Expense(1, 200, 1, "Good Food Yummy Yummy", Type.FOOD, Status.DENIED);
        Mockito.when(expenseDAO.getExpenseById(1)).thenReturn(expense);
        Mockito.when(expenseDAO.updateExpenseStatus(1, Status.APPROVED)).thenReturn(expense);
        Assertions.assertThrows(RuntimeException.class, () -> {
           expenseService.modifyExpenseStatus(1, Status.APPROVED);
        });
    }

    @Test
    public void delete_expense_status_must_be_pending() {
        Expense expense = new Expense(1, 200, 1, "Good Food Yummy Yummy", Type.FOOD, Status.APPROVED);
        Mockito.when(expenseDAO.getExpenseById(1)).thenReturn(expense);
        Mockito.when(expenseDAO.deleteExpense(1)).thenReturn(true);
        Assertions.assertThrows(RuntimeException.class, () -> {
            expenseService.deleteExpense(1);
        });
    }

}
