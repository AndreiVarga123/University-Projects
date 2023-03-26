package group.lab1.Service;

import group.lab1.Model.Beer;

import java.util.List;

public interface Service<T> {
    List<Long> getAll();

    T save(T obj);

    T getById(Long id);

    T update(T obj);

    void delete(Long id);
}
