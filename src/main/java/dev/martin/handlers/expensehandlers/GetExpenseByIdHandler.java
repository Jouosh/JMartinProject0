package dev.martin.handlers.expensehandlers;

import com.google.gson.Gson;
import dev.martin.app.App;
import dev.martin.entities.Expense;
import io.javalin.http.Context;
import io.javalin.http.Handler;
import org.jetbrains.annotations.NotNull;

public class GetExpenseByIdHandler implements Handler {

    @Override
    public void handle(@NotNull Context ctx) throws Exception {

        int id = Integer.parseInt(ctx.pathParam("id"));
        Expense expense = App.expenseService.retrieveExpenseById(id);

        if (expense == null) {
            ctx.status(404);
            ctx.result("No expense of id " + id + " was found");
        }
        else {
            Gson gson = new Gson();
            String outJson = gson.toJson(expense);
            ctx.result(outJson);
        }

    }
}
