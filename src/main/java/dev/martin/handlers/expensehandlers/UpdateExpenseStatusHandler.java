package dev.martin.handlers.expensehandlers;

import com.google.gson.Gson;
import dev.martin.app.App;
import dev.martin.entities.Expense;
import dev.martin.entities.Status;
import io.javalin.http.Context;
import io.javalin.http.Handler;
import org.jetbrains.annotations.NotNull;

public class UpdateExpenseStatusHandler implements Handler {

    @Override
    public void handle(@NotNull Context ctx) throws Exception {

        //Pull both path params from route
        int id = Integer.parseInt(ctx.pathParam("id"));
        String statusString = ctx.pathParam("status").toUpperCase();

        //Check that id exists, 404 if not
        if (App.expenseService.retrieveExpenseById(id) == null) {
            ctx.status(404);
            ctx.result("No expense with id of " + id + " was found.");
            return;
        }

        //If status path param is a valid status, then convert from string to status, otherwise pull out
        Status status;
        try {
            status = Status.valueOf(statusString);
        } catch(IllegalArgumentException e) {
            ctx.result("Status query can only be approved or denied");
            return;
        }

        //update the status, output the new object
        Expense expense = App.expenseService.modifyExpenseStatus(id, status);
        Gson gson = new Gson();
        String outJson = gson.toJson(expense);
        ctx.status(200);
        ctx.result(outJson);

    }
}
