package dev.martin.app;

import dev.martin.data.EmployeeDAOLocal;
import dev.martin.data.ExpenseDAOLocal;
import dev.martin.handlers.employeehandlers.*;
import dev.martin.handlers.expensehandlers.CreateExpenseHandler;
import dev.martin.handlers.expensehandlers.GetExpenseByIdHandler;
import dev.martin.handlers.expensehandlers.GetExpensesHandler;
import dev.martin.services.EmployeeService;
import dev.martin.services.EmployeeServiceImpl;
import dev.martin.services.ExpenseService;
import dev.martin.services.ExpenseServiceImpl;
import io.javalin.Javalin;

public class App {

    public static EmployeeService employeeService = new EmployeeServiceImpl(new EmployeeDAOLocal());
    public static ExpenseService expenseService = new ExpenseServiceImpl(new ExpenseDAOLocal());

    public static void main(String[] args) {

        //Create the app
        Javalin app = Javalin.create();

        //Employee handlers
        CreateEmployeeHandler createEmployeeHandler = new CreateEmployeeHandler();
        GetAllEmployeesHandler getAllEmployeesHandler = new GetAllEmployeesHandler();
        GetEmployeeByIdHandler getEmployeeByIdHandler = new GetEmployeeByIdHandler();
        UpdateEmployeeHandler updateEmployeeHandler = new UpdateEmployeeHandler();
        DeleteEmployeeByIdHandler deleteEmployeeByIdHandler = new DeleteEmployeeByIdHandler();

        //Expense handlers
        CreateExpenseHandler createExpenseHandler = new CreateExpenseHandler();
        GetExpensesHandler getExpensesHandler = new GetExpensesHandler();
        GetExpenseByIdHandler getExpenseByIdHandler = new GetExpenseByIdHandler();

        //Employee routes
        app.post("/employees", createEmployeeHandler);
        app.get("/employees", getAllEmployeesHandler);
        app.get("/employees/{id}", getEmployeeByIdHandler);
        app.put("/employees/{id}", updateEmployeeHandler);
        app.delete("employees/{id}", deleteEmployeeByIdHandler);

        //Expense routes
        app.post("/expenses", createExpenseHandler);
        app.get("/expenses", getExpensesHandler);
        app.get("/expenses/{id}", getExpenseByIdHandler);

        app.start();

    }

}
