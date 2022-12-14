package dev.martin.handlers;

import dev.martin.app.App;
import dev.martin.entities.Status;
import io.javalin.http.Context;
import io.javalin.http.Handler;
import org.jetbrains.annotations.NotNull;

public class DeleteExpenseByIdHandler implements Handler {

    @Override
    public void handle(@NotNull Context ctx) throws Exception {

        //Get id from path, make sure it's valid
        int id = Integer.parseInt(ctx.pathParam("id"));

        if (App.expenseService.retrieveExpenseById(id) == null) {
            ctx.status(404);
            ctx.result("Expense with id " + id + " not found");
        }
        else if (App.expenseService.retrieveExpenseById(id).getStatus() != Status.PENDING) {
            ctx.status(422);
            ctx.result("Expenses that have already been approved or denied cannot be deleted.");
        }
        //If valid, delete, and make body confirm
        else {
            App.expenseService.deleteExpense(id);
            ctx.result("Deleted Expense");
        }
    }
}
