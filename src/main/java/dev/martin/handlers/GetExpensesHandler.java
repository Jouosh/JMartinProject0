package dev.martin.handlers;

import com.google.gson.Gson;
import dev.martin.app.App;
import dev.martin.entities.Expense;
import dev.martin.entities.Status;
import io.javalin.http.Context;
import io.javalin.http.Handler;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class GetExpensesHandler implements Handler {

    @Override
    public void handle(@NotNull Context ctx) {

        //try to get status from path, if exists and is valid put status results into list
        String statusString = ctx.queryParam("status");
        List<Expense> expenses;
        if (statusString != null) {
            try {
                statusString = statusString.toUpperCase();
                expenses = App.expenseService.retrieveExpenseByStatus(Status.valueOf(statusString));
            } catch (IllegalArgumentException e) {
                ctx.result("Status queries can only be approved, denied, or pending");
                return;
            }
        }
        //if status is not in path get all expenses into list
        else {
            expenses = App.expenseService.retrieveAllExpenses();
        }

        //turn list into json and put into body
        Gson gson = new Gson();
        String outJson = gson.toJson(expenses);

        ctx.status(200);
        ctx.result(outJson);

    }
}
