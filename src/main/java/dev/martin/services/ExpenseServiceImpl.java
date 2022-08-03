package dev.martin.services;

import dev.martin.data.ExpenseDAO;
import dev.martin.data.ExpenseDAOLocal;
import dev.martin.entities.Expense;

import java.util.List;

public class ExpenseServiceImpl implements ExpenseService{

    private ExpenseDAO expenseDAO;

    public ExpenseServiceImpl(ExpenseDAO expenseDAO) {
        this.expenseDAO = expenseDAO;
    }

    @Override
    public Expense registerExpense(Expense expense) {

        //Check that there is a valid issuer
        if (expense.getIssuer() <= 0) {
            throw new RuntimeException("Expenses must have the issuing employee's id attached");
        }

        //If status is not pending, make it pending
        if (expense.getStatus() != Expense.Status.PENDING) {
            expense.setStatus(Expense.Status.PENDING);
        }

        //Check that the amount is valid
        if (expense.getAmount() < 0) {
            throw new RuntimeException("Expenses must have a positive amount spent");
        }

        //Once checked, send to data layer, return savedExpense
        Expense savedExpense = expenseDAO.createExpense(expense);
        return savedExpense;
    }

    @Override
    public List<Expense> retrieveAllExpenses() {
        return expenseDAO.getAllExpenses();
    }

    @Override
    public Expense retrieveExpenseById(int id) {
        return expenseDAO.getExpenseById(id);
    }

    @Override
    public List<Expense> retrieveExpenseByStatus(Expense.Status status) {
        return expenseDAO.getExpenseByStatus(status);
    }
}
