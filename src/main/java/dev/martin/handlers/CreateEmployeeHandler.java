package dev.martin.handlers;

import com.google.gson.Gson;
import dev.martin.app.App;
import dev.martin.entities.Employee;
import io.javalin.http.Context;
import io.javalin.http.Handler;
import org.jetbrains.annotations.NotNull;

public class CreateEmployeeHandler implements Handler {

    @Override
    public void handle(@NotNull Context ctx) throws Exception {

        //Get json input and turn into employee object
        String inJson = ctx.body();
        Gson gson = new Gson();
        Employee employee = gson.fromJson(inJson, Employee.class);

        //Send object to service layer
        Employee registeredEmployee = App.employeeService.registerEmployee(employee);

        //Turn registered object into json, set result and status
        String outJson = gson.toJson(registeredEmployee);
        ctx.status(201);
        ctx.result(outJson);

    }

}
