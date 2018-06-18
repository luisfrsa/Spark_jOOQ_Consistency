package controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import config.Config;
import domain.User;
import service.UserService;

import java.util.List;

import static java.lang.String.format;
import static spark.Spark.*;

public class UserController {

    //    private static UserService userService = new UserService();
    private static ObjectMapper om = new ObjectMapper();
    private static UserService userService = new UserService();

    public UserController() {

        get("/", (request, response) -> {
            userService.add(1L, "nome");
            System.out.println(userService.findAll().toString());
            return format("Welcome by: id: %s port: %s", Config.id, Config.port);
        });

        post("/user/add", (request, response) -> {
            Long id = Long.parseLong(request.queryParams("id"));
            String name = request.queryParams("name");
            User user = userService.add(id, name);
            response.status(201); // 201 Created
            return om.writeValueAsString(user);

        });

        get("/user/all", (request, response) -> {
            List<User> user = userService.findAll();
            if (user != null) {
                return om.writeValueAsString(user);
            } else {
                response.status(404);
                return om.writeValueAsString("user not found");
            }
        });
    }
}
