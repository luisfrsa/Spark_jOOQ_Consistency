package repository;

import java.util.LinkedList;

public interface RepositoryInterface<T> {
    T getById(Long id);

    LinkedList<T> getByAll();

    T save(T entity);

    boolean delete(T entity);

    boolean delete(Long id);
}