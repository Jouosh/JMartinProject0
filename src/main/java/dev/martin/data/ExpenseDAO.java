package dev.martin.data;

import dev.martin.entities.Expense;

import java.util.List;

public interface ExpenseDAO {

    //Create
    Expense createExpense(Expense expense);

    //Read
    List<Expense> getAllExpenses();
    Expense getExpenseById(int id);
    List<Expense> getExpenseByStatus(Expense.Status status);

    //Update
    Expense updateExpense(Expense expense);

    Expense updateExpenseStatus(int id, Expense.Status status);

}
