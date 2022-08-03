package dev.martin.handlers;

import com.google.gson.Gson;
import dev.martin.app.App;
import dev.martin.entities.Employee;
import io.javalin.http.Context;
import io.javalin.http.Handler;
import org.jetbrains.annotations.NotNull;

public class GetEmployeeByIdHandler implements Handler {

    @Override
    public void handle(@NotNull Context ctx) throws Exception {

        //Get employee and id
        int id = Integer.parseInt(ctx.pathParam("id"));
        Employee employee = App.employeeService.retrieveEmployeeById(id);

        //if employee not found, 404 error, and end
        if (employee == null) {
            ctx.status(404);
            ctx.result("No employee with id of " + id + " was found.");
        }
        //otherwise, make result json form of requested employee
        else {
            Gson gson = new Gson();
            String json = gson.toJson(employee);
            ctx.result(json);
        }

    }

}
