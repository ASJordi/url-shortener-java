package dev.asjordi.service;

import java.util.List;
import java.util.Optional;

public interface IService<T> {
    List<T> findAll();
    Optional<T> findById(Integer id);
    Optional<T> findByHash(String hash);
    void save(T p);
    void delete(Integer id);
}
