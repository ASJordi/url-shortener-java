package dev.asjordi.repository;

import dev.asjordi.configs.Repository;
import dev.asjordi.configs.RepositoryJpa;
import dev.asjordi.models.Url;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import java.util.List;

@Repository
@RepositoryJpa
public class UrlRepositoryJpa implements IRepository<Url> {

    @Inject
    private EntityManager em;

    @Override
    public List<Url> findAll() throws Exception {
        return em.createQuery("SELECT u FROM Url u").getResultList();
    }

    @Override
    public Url findById(Integer id) throws Exception {
        return em.find(Url.class, id);
    }

    @Override
    public Url findByHash(String hash) throws Exception {
        return em
                .createQuery("SELECT u FROM Url u WHERE u.hash =:hash", Url.class)
                .setParameter("hash", hash).getSingleResult();
    }

    @Override
    public void save(Url url) throws Exception {
        if (url.getId() != null && url.getId() > 0) em.merge(url);
        else em.persist(url);
    }

    @Override
    public void delete(Integer id) throws Exception {
        var url = findById(id);
        if (url != null) em.remove(url);
    }
}
