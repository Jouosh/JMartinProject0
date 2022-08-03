package dev.martin.data;

import dev.martin.entities.Expense;
import dev.martin.entities.Status;

import java.util.List;

public interface ExpenseDAO {

    //Create
    Expense createExpense(Expense expense);

    //Read
    List<Expense> getAllExpenses();
    Expense getExpenseById(int id);
    List<Expense> getExpenseByStatus(Status status);
    List<Expense> getExpenseByIssuer(int issuer);

    //Update
    Expense updateExpense(Expense expense);
    Expense updateExpenseStatus(int id, Status status);

    //Delete
    boolean deleteExpense(int id);

}
