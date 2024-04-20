package dev.asjordi.service;

import dev.asjordi.configs.Service;
import dev.asjordi.exceptions.ServiceJDBCException;
import dev.asjordi.interceptors.TransactionalJpa;
import dev.asjordi.models.Url;
import dev.asjordi.repository.IRepository;
import jakarta.inject.Inject;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Service
@TransactionalJpa
public class UrlService implements IService<Url> {

    @Inject
    IRepository<Url> repo;

    @Override
    public List<Url> findAll() {
        try {
            return this.repo.findAll();
        } catch (Exception e) {
            throw new ServiceJDBCException(e.getMessage(), e.getCause());
        }
    }

    @Override
    public Optional<Url> findById(Integer id) {
        try {
            return Optional.ofNullable(this.repo.findById(id));
        } catch (Exception e) {
            throw new ServiceJDBCException(e.getMessage(), e.getCause());
        }
    }

    @Override
    public Optional<Url> findByHash(String hash) {
        try {
            return Optional.ofNullable(this.repo.findByHash(hash));
        } catch (Exception e) {
            throw new ServiceJDBCException(e.getMessage(), e.getCause());
        }
    }

    @Override
    public void save(Url p) {
        try {
            this.repo.save(p);
        } catch (Exception e) {
            throw new ServiceJDBCException(e.getMessage(), e.getCause());
        }
    }

    @Override
    public void delete(Integer id) {
        try {
            this.repo.delete(id);
        } catch (Exception e) {
            throw new ServiceJDBCException(e.getMessage(), e.getCause());
        }
    }
}
