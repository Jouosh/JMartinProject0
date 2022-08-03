package dev.martin.handlers.employeehandlers;

import com.google.gson.Gson;
import dev.martin.app.App;
import dev.martin.entities.Employee;
import io.javalin.http.Context;
import io.javalin.http.Handler;
import org.jetbrains.annotations.NotNull;

public class UpdateEmployeeHandler implements Handler {


    @Override
    public void handle(@NotNull Context ctx) throws Exception {

        //get id from path
        int id = Integer.parseInt(ctx.pathParam("id"));

        //if employee not found, 404 error and end with message
        if (App.employeeService.retrieveEmployeeById(id) == null) {
            ctx.status(404);
            ctx.result("No employee with id of " + id + " was found.");
            return;
        }

        //Otherwise convert json in into employee
        String inJson = ctx.body();
        Gson gson = new Gson();
        Employee employee = gson.fromJson(inJson, Employee.class);

        //if path id and employee id differ, path wins
        if (employee.getId() != id) {
            employee.setId(id);
        }

        //modify employee in list, then output modified employee
        Employee modifiedEmployee = App.employeeService.modifyEmployee(employee);
        String outJson = gson.toJson(modifiedEmployee);
        ctx.result(outJson);

    }
}
