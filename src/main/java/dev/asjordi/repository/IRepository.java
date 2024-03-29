package dev.asjordi.repository;

import java.sql.SQLException;
import java.util.List;

public interface IRepository<T> {
    List<T> findAll() throws SQLException;
    T findById(Integer id) throws SQLException;
    T findByHash(String hash) throws SQLException;
    void save(T t) throws SQLException;
    void delete(Integer id) throws SQLException;
}
