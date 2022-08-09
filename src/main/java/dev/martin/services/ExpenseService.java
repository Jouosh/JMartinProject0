package dev.martin.services;

import dev.martin.entities.Expense;
import dev.martin.entities.Status;

import java.util.List;

public interface ExpenseService {

    //Create
    public Expense registerExpense(Expense expense);

    //Read
    public List<Expense> retrieveAllExpenses();
    public Expense retrieveExpenseById(int id);
    public List<Expense> retrieveExpenseByStatus(Status status);
    public List<Expense> retrieveExpenseByIssuer(int issuer);

    //Update
    public Expense modifyExpense(Expense expense);
    public Expense modifyExpenseStatus(int id, Status status);

    //Delete
    public boolean deleteExpense(int id);

}
