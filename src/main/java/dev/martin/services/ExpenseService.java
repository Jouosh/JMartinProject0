package dev.martin.services;

import dev.martin.entities.Expense;

import java.util.List;

public interface ExpenseService {

    public Expense registerExpense(Expense expense);

    public List<Expense> retrieveAllExpenses();
    public Expense retrieveExpenseById(int id);
    public List<Expense> retrieveExpenseByStatus(Expense.Status status);

}
