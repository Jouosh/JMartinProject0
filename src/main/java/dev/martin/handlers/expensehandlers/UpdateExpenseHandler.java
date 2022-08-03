package dev.martin.handlers.expensehandlers;

import com.google.gson.Gson;
import dev.martin.app.App;
import dev.martin.entities.Expense;
import io.javalin.http.Context;
import io.javalin.http.Handler;
import org.jetbrains.annotations.NotNull;

public class UpdateExpenseHandler implements Handler {
    @Override
    public void handle(@NotNull Context ctx) throws Exception {

        int id = Integer.parseInt(ctx.pathParam("id"));
        if (App.expenseService.retrieveExpenseById(id) == null) {
            ctx.status(404);
            ctx.result("No expense with id of " + id + " was found.");
            return;
        }

        Gson gson = new Gson();
        String inJson = ctx.body();
        Expense expense = gson.fromJson(inJson, Expense.class);

        if (expense.getId() != id) {
            expense.setId(id);
        }

        Expense updatedExpense = App.expenseService.modifyExpense(expense);
        String outJson = gson.toJson(updatedExpense);
        ctx.result(outJson);

    }
}
