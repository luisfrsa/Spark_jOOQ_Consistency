//package DAO;
//
//
//import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;
//
//import java.util.Map;
//
//public class JdbcDAO implements DAO {
//
//    private static MysqlDataSource dataSource;
//
//    static {
//
//        try {
//
//            dataSource = new MysqlDataSource();
//            dataSource.setUser("root");
//            dataSource.setPassword("123");
//            dataSource.setServerName("localhost");
//            dataSource.setPort(3306);
//            dataSource.setDatabaseName("spark_app");
//
//        } catch (Exception e) {
//
//            throw new ExceptionInInitializerError(e);
//
//        }
//
//    }
//
//    @Override
//    public boolean addUser(Map<String, Object> data) {
//        QueryRunner run = new QueryRunner( dataSource );
//
//        try	{
//
//            int inserts = run.update( "INSERT INTO Person (first_name, last_name) VALUES (?,?)", data.get("first_name"), data.get("last_name"));
//
//        } catch(SQLException sqle) {
//
//            throw new RuntimeException("Problem updating", sqle);
//
//        }
//
//        return true;
//    }
//
//}
