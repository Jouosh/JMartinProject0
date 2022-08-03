package dev.martin.services;

import dev.martin.entities.Expense;
import dev.martin.entities.Status;

import java.util.List;

public interface ExpenseService {

    public Expense registerExpense(Expense expense);

    public List<Expense> retrieveAllExpenses();
    public Expense retrieveExpenseById(int id);
    public List<Expense> retrieveExpenseByStatus(Status status);

    public Expense modifyExpense(Expense expense);
    public Expense modifyExpenseStatus(int id, Status status);

    public boolean deleteExpense(int id);

}
