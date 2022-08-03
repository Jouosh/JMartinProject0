package dev.martin.handlers.expensehandlers;

import com.google.gson.Gson;
import dev.martin.app.App;
import dev.martin.entities.Expense;
import io.javalin.http.Context;
import io.javalin.http.Handler;
import org.jetbrains.annotations.NotNull;

public class CreateExpenseHandler implements Handler {

    @Override
    public void handle(@NotNull Context ctx) throws Exception {

        //Get info from jso and turn it into an expense
        String inJson = ctx.body();
        Gson gson = new Gson();
        Expense expense = gson.fromJson(inJson, Expense.class);

        //Send object down to service layer, get back result
        Expense registeredExpense = App.expenseService.registerExpense(expense);

        //Send info back out with json
        String outJson = gson.toJson(registeredExpense);
        ctx.status(201);
        ctx.result(outJson);



    }

}
