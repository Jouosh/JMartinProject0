package dev.martin.data;

import dev.martin.entities.Expense;
import dev.martin.entities.Status;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ExpenseDAOLocal implements ExpenseDAO {

    private Map<Integer, Expense> expenseTable = new HashMap();
    private int idMaker = 1;

    //Create
    @Override
    public Expense createExpense(Expense expense) {

        expense.setId(idMaker);
        idMaker++;

        expenseTable.put(expense.getId(), expense);
        return expense;
    }

    //Read
    @Override
    public List<Expense> getAllExpenses() {

        List<Expense> expenses = new ArrayList();
        expenses.addAll(expenseTable.values());
        return expenses;
    }

    @Override
    public Expense getExpenseById(int id) {
        return expenseTable.get(id);
    }

    @Override
    public List<Expense> getExpenseByStatus(Status status) {
        List<Expense> expenses = new ArrayList();

        for (Map.Entry<Integer, Expense> entry : expenseTable.entrySet()) {
            Expense expense = entry.getValue();
            if (expense.getStatus() == status) {
                expenses.add(expense);
            }
        }

        return expenses;
    }

    @Override
    public List<Expense> getExpenseByIssuer(int issuer) {

        List<Expense> expenses = new ArrayList();
        for (Map.Entry<Integer, Expense> entry : expenseTable.entrySet()) {
            Expense expense = entry.getValue();
            if (expense.getIssuer() == issuer) {
                expenses.add(expense);
            }
        }

        return expenses;

    }

    //Update
    @Override
    public Expense updateExpense(Expense expense) {
        expenseTable.put(expense.getId(), expense);
        return expense;
    }

    @Override
    public Expense updateExpenseStatus(int id, Status status) {
        expenseTable.get(id).setStatus(status);
        return expenseTable.get(id);
    }

    //Delete
    @Override
    public boolean deleteExpense(int id) {

        Expense expense = expenseTable.remove(id);

        if (expense == null) {
            return false;
        }

        return true;

    }


}
