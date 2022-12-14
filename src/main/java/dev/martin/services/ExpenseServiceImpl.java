package dev.martin.services;

import dev.martin.data.ExpenseDAO;
import dev.martin.data.ExpenseDAOLocal;
import dev.martin.entities.Expense;
import dev.martin.entities.Status;

import java.util.List;

public class ExpenseServiceImpl implements ExpenseService{

    //Data layer connection
    private ExpenseDAO expenseDAO;

    //Constructor
    public ExpenseServiceImpl(ExpenseDAO expenseDAO) {
        this.expenseDAO = expenseDAO;
    }

    //Create
    @Override
    public Expense registerExpense(Expense expense) {

        //Check that there is a valid issuer
        if (expense.getIssuer() <= 0) {
            throw new RuntimeException("Expenses must have the issuing employee's id attached");
        }

        //If status is not pending, make it pending
        if (expense.getStatus() != Status.PENDING) {
            expense.setStatus(Status.PENDING);
        }

        //Check that the amount is valid
        if (expense.getAmount() < 0) {
            throw new RuntimeException("Expenses must have a positive amount spent");
        }

        //Once checked, send to data layer, return savedExpense
        Expense savedExpense = expenseDAO.createExpense(expense);
        return savedExpense;
    }

    //Read
    @Override
    public List<Expense> retrieveAllExpenses() {
        return expenseDAO.getAllExpenses();
    }

    @Override
    public Expense retrieveExpenseById(int id) {
        return expenseDAO.getExpenseById(id);
    }

    @Override
    public List<Expense> retrieveExpenseByStatus(Status status) {
        return expenseDAO.getExpenseByStatus(status);
    }

    @Override
    public List<Expense> retrieveExpenseByIssuer(int issuer) {
        return expenseDAO.getExpenseByIssuer(issuer);
    }

    //Update
    @Override
    public Expense modifyExpense(Expense expense) {

        //If the previous expense was not pending, it can not be changed, this checks for that
        Expense oldExpense = expenseDAO.getExpenseById(expense.getId());
        if (oldExpense.getStatus() != Status.PENDING) {
            throw new RuntimeException("An expense can not be edited once its been approved or denied");
        }

        //Check that there is a valid issuer
        if (expense.getIssuer() <= 0) {
            throw new RuntimeException("Expenses must have the issuing employee's id attached");
        }

        //Check that the amount is valid
        if (expense.getAmount() < 0) {
            throw new RuntimeException("Expenses must have a positive amount spent");
        }

        //call data layer and return
        Expense newExpense = expenseDAO.updateExpense(expense);
        return newExpense;

    }

    @Override
    public Expense modifyExpenseStatus(int id, Status status) {

        //Check that expense is still pending, throw exception if not
        if (expenseDAO.getExpenseById(id).getStatus() != Status.PENDING) {
            throw new RuntimeException("An expense can not be edited once its been approved or denied");
        }

        return expenseDAO.updateExpenseStatus(id, status);
    }

    //delete
    @Override
    public boolean deleteExpense(int id) {

        //Check that expense is pending, throw if not
        if (expenseDAO.getExpenseById(id).getStatus() != Status.PENDING) {
            throw new RuntimeException("An expense can not be deleted once its been approved or denied");
        }

        return expenseDAO.deleteExpense(id);

    }
}
