package dev.martin.handlers;

import com.google.gson.Gson;
import dev.martin.app.App;
import dev.martin.entities.Expense;
import io.javalin.http.Context;
import io.javalin.http.Handler;
import org.jetbrains.annotations.NotNull;

public class CreateExpenseWithIssuerHandler implements Handler {

    @Override
    public void handle(@NotNull Context ctx) throws Exception {

        //get issuer from path and make sure its valid
        int issuer = Integer.parseInt(ctx.pathParam("issuer"));

        if (App.employeeService.retrieveEmployeeById(issuer) == null) {
            ctx.status(404);
            ctx.result("Employee of id " + issuer + " was not found.");
            return;
        }

        //Get info from json and turn it into an expense
        String inJson = ctx.body();
        Gson gson = new Gson();
        Expense expense = gson.fromJson(inJson, Expense.class);
        expense.setIssuer(issuer);

        Expense registeredExpense = App.expenseService.registerExpense(expense);

        String outJson = gson.toJson(registeredExpense);
        ctx.status(201);
        ctx.result(outJson);
    }

}
