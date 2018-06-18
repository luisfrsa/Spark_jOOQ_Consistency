package repository;

import DAO.HibernateUtil;
import domain.User;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import javax.persistence.EntityManager;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

public class UserRepository implements RepositoryInterface<User> {

    private SessionFactory sf = null;
    private EntityManager session = null;

    public UserRepository() {
        this.sf = HibernateUtil.getSessionFactory();
        this.session = this.sf.createEntityManager();
    }

    public User getById(Long id) {
        User user = session.find(User.class, id);
        return user;
    }

    @Override
    public List<User> getAll() {
        System.out.println("-------------------------");
        System.out.println("GET ALL USER");
        List<User> users = session.createQuery("FROM User").getResultList();
        return users;
    }

    @Override
    public User save(User entity) {
        System.out.println("-------------------------");
        System.out.println("CRIANDO USER");
        session.getTransaction().begin();
        session.persist(entity);
        session.getTransaction().commit();
        return entity;
    }

    @Override
    public boolean delete(User entity) {

        if (Objects.nonNull(entity)) {
            session.getTransaction().begin();
            session.remove(entity);
            session.getTransaction().commit();
            return true;
        }
        return false;
    }

    @Override
    public boolean delete(Long id) {
        return delete(getById(id));
    }
}
