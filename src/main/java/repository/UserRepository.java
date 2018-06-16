package repository;

import java.util.LinkedList;

public class UserRepository implements RepositoryInterface {

    @Override
    public Object getById(Long id) {
        return null;
    }

    @Override
    public LinkedList getByAll() {
        return null;
    }

    @Override
    public Object save(Object entity) {
        return null;
    }

    @Override
    public boolean delete(Object entity) {
        return false;
    }

    @Override
    public boolean delete(Long id) {
        return false;
    }
}
