package dev.martin.handlers;

import dev.martin.app.App;
import io.javalin.http.Context;
import io.javalin.http.Handler;
import org.jetbrains.annotations.NotNull;

public class DeleteEmployeeByIdHandler implements Handler {
    @Override
    public void handle(@NotNull Context ctx) throws Exception {

        //Get id from param and try to delete
        int id = Integer.parseInt(ctx.pathParam("id"));
        boolean result = App.employeeService.deleteEmployee(id);

        //Output pass or fail based on result
        if (result) {
            ctx.result("Deleted employee");
        }
        else {
            ctx.status(404);
            ctx.result("Employee could not be found");
        }

    }
}
