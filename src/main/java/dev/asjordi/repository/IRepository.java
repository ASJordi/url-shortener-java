package dev.asjordi.repository;

import java.util.List;

public interface IRepository<T> {
    List<T> findAll() throws Exception;
    T findById(Integer id) throws Exception;
    T findByHash(String hash) throws Exception;
    void save(T t) throws Exception;
    void delete(Integer id) throws Exception;
}
