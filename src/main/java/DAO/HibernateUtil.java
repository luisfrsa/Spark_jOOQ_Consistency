package DAO;

import com.fasterxml.classmate.AnnotationConfiguration;
import config.Config;
import domain.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.Objects;
import java.util.Properties;


//https://www.concretepage.com/hibernate/configure_hibernate_without_hibernate_cfg_xml
public class HibernateUtil {
    private static SessionFactory sessionFactory = null;
    private static Session session = null;

//    public static Session openSession(String host, int port, String base, String login, String senha) {
//        Configuration config = new Configuration().configure(new HibernateUtil().getPath());
//        config.setProperty("hibernate.connection.url", "jdbc:mysql://lcoalhost:3306/sd_database_1");
//        config.setProperty("hibernate.connection.username", "root");
//        config.setProperty("hibernate.connection.password", "123");
//        sessionFactory = config.buildSessionFactory();
//        threadLocal.set(sessionFactory.openSession());
//        return threadLocal.get();
//    }

    private static void build() {
        Properties prop = new Properties();

        prop.setProperty("hibernate.connection.url", "jdbc:mysql://localhost:3306/sd_database_1");

        prop.setProperty("dialect", "org.hibernate.dialect.MySQLDialect");

        prop.setProperty("hibernate.connection.username", "root");
        prop.setProperty("hibernate.connection.password", "123");
        prop.setProperty("hibernate.connection.driver_class", "com.mysql.jdbc.Driver");
        prop.setProperty("show_sql", "true");


        Configuration configuration = new Configuration()
                .addPackage("domain")
                .addProperties(prop)
                .addAnnotatedClass(User.class);


        HibernateUtil.sessionFactory = configuration.buildSessionFactory();
        HibernateUtil.session = HibernateUtil.sessionFactory.openSession();
    }

    public static Session getSession() {
        if (Objects.isNull(HibernateUtil.session)) {
            build();
        }
        return HibernateUtil.session;
    }


    public static SessionFactory getSessionFactory() {
        if (Objects.isNull(HibernateUtil.sessionFactory)) {
            build();
        }
        return HibernateUtil.sessionFactory;
    }
}