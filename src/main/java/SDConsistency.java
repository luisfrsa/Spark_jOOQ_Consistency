import controller.UserController;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import service.UserService;

import javax.persistence.EntityManager;

import static spark.Spark.port;

public class SDConsistency {



    public static void main(String[] args) {
        port(8080);
        new UserController(new UserService());


    }

}