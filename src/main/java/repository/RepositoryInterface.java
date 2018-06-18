package repository;

import java.util.List;

public interface RepositoryInterface<T> {

    T getById(Long id);

    List<T> getAll();

    T save(T entity);

    boolean delete(T entity);

    boolean delete(Long id);
}