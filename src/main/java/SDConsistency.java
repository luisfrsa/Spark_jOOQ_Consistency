import controller.UserController;
import service.UserService;

import static spark.Spark.port;

public class SDConsistency {



    public static void main(String[] args) {
        port(8080);
        new UserController(new UserService());
    }

}