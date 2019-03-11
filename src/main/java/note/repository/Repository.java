package note.repository;

import note.utils.ClasaException;

import java.util.List;

public interface Repository<T> {
    void add(T elem) throws ClasaException;

    T getById(int id);

    List<T> getAll();
}
