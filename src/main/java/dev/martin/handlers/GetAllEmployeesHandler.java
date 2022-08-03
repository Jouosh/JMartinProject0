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

        //Create variables, one list, and one string for output
        List<Employee> employeeList = App.employeeService.retrieveAllEmployees();
        String result = "";

        //Convert list into json, put in result
        Gson gson = new Gson();
        for (int i = 0; i < employeeList.size(); i++) {
            String json = gson.toJson(employeeList.get(i));
            result += json + "\n";
        }

        //Output list in json form
        ctx.status(200);
        ctx.result(result);

    }
}
