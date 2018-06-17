package repository;

import domain.User;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import javax.persistence.EntityManager;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

public class UserRepository implements RepositoryInterface<User> {

    private SessionFactory sf = new Configuration().configure().buildSessionFactory();

    private EntityManager session = sf.createEntityManager();

    public User getById(Long id) {
        User user = session.find(User.class, id);
        return user;
    }

    @Override
    public List<User> getByAll() {
        List<User> users = session.createQuery("FROM User").getResultList();
        return users;
    }

    @Override
    public User save(User entity) {
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
