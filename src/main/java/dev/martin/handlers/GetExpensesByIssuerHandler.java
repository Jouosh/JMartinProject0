package dev.martin.handlers;

import com.google.gson.Gson;
import dev.martin.app.App;
import dev.martin.entities.Expense;
import io.javalin.http.Context;
import io.javalin.http.Handler;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class GetExpensesByIssuerHandler implements Handler {

    @Override
    public void handle(@NotNull Context ctx) throws Exception {

        //get issuer from path, and check if valid
        int issuer = Integer.parseInt(ctx.pathParam("issuer"));

        if (App.employeeService.retrieveEmployeeById(issuer) == null) {
            ctx.status(404);
            ctx.result("Employee with id " + issuer + " not found");
            return;
        }

        //If valid, get results and put into body
        List<Expense> expenseList = App.expenseService.retrieveExpenseByIssuer(issuer);

        Gson gson = new Gson();
        String outJson = gson.toJson(expenseList);
        ctx.status(200);
        ctx.result(outJson);

    }

}
