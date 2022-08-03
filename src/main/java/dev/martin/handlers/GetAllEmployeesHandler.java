package dev.martin.handlers;

import com.google.gson.Gson;
import dev.martin.app.App;
import dev.martin.entities.Employee;
import io.javalin.http.Context;
import io.javalin.http.Handler;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class GetAllEmployeesHandler implements Handler {


    @Override
    public void handle(@NotNull Context ctx) throws Exception {

        //Create variable, one list
        List<Employee> employeeList = App.employeeService.retrieveAllEmployees();
        Gson gson = new Gson();
        String outJson = gson.toJson(employeeList);

        //Output list in json form
        ctx.status(200);
        ctx.result(outJson);

    }
}
