package Config;
//https://www.jooq.org/doc/3.11/manual/getting-started/tutorials/jooq-in-7-steps/
import domain.User;

import static test.generated.Tables.*;
import static org.jooq.impl.DSL.*;

import java.sql.*;

import org.jooq.*;
import org.jooq.impl.*;

public class JOOQ {
    void connect() {

        String userName = "root";
        String password = "";
        String url = "jdbc:mysql://localhost:3306/library";

        // Connection is the only JDBC resource that we need
        // PreparedStatement and ResultSet are handled by jOOQ, internally
        try (Connection conn = DriverManager.getConnection(url, userName, password)) {
            DSLContext create = DSL.using(conn, SQLDialect.MYSQL);
            Result<User> result = create.select().from(AUTHOR).fetch();

            for (User r : result) {
                Integer id = r.getId(AUTHOR.ID);
                String firstName = r.getName(AUTHOR.FIRST_NAME);
                String lastName = r.getEmail(AUTHOR.LAST_NAME);

                System.out.println("ID: " + id + " first name: " + firstName + " last name: " + lastName);
            }
        }

        // For the sake of this tutorial, let's keep exception handling simple
        catch (Exception e) {
            e.printStackTrace();
        }

    }
}

