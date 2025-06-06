package ir.maktabhw19.repository.base;

import ir.maktabhw19.domains.base.BaseEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

public abstract class SimpleJpaRepository<T extends BaseEntity<ID>, ID>
        implements CrudRepository<T, ID> {


    protected final EntityManager entityManager;

    protected Class<T> domainClass;

    public SimpleJpaRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }


    protected abstract Class<T> getDomainClass();


    @Override
    public T save(T entity) {
        if (entity.getId() == null) {
            entityManager.persist(entity);
        } else {
            entity = entityManager.merge(entity);
        }
        return entity;
    }

    @Override
    public List<T> saveAll(Collection<T> entities) {
        List<T> savedEntities = new ArrayList<>();
        for (T entity : entities) {
            savedEntities.add(save(entity));
        }
        return savedEntities;
    }

    @Override
    public Optional<T> findById(ID id) {
        return Optional.ofNullable(entityManager.find(getDomainClass(), id));
    }

    @Override
    public List<T> findAll() {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<T> query = cb.createQuery(getDomainClass());
        Root<T> from = query.from(getDomainClass());
        query.select(from);
        return entityManager.createQuery(query).getResultList();
    }

    @Override
    public List<T> findAllById(Iterable<ID> ids) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<T> query = cb.createQuery(getDomainClass());
        Root<T> from = query.from(getDomainClass());
        query.select(from);

        query.where(
                cb.in(from.get("id")).value(ids)
        );

        return entityManager.createQuery(query).getResultList();
    }

    @Override
    public Long countAll() {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Long> query = cb.createQuery(Long.class);
        Root<T> from = query.from(getDomainClass());
        query.select(cb.count(from));
        return entityManager.createQuery(query).getSingleResult();
    }

    @Override
    public void deleteById(ID id) {
        findById(id).ifPresent(entityManager::remove);
    }

    @Override
    public void deleteAllById(Iterable<ID> ids) {
        ids.forEach(this::deleteById);
    }

    @Override
    public boolean existsById(ID id) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Long> query = cb.createQuery(Long.class);
        Root<T> from = query.from(getDomainClass());
        query.select(cb.count(from));
        query.where(cb.equal(from.get("id"), id));
        return entityManager.createQuery(query).getSingleResult() > 0;
    }

    @Override
    public void beginTransaction() {
        if (!entityManager.getTransaction().isActive()) {
            entityManager.getTransaction().begin();
        }
    }

    @Override
    public void commitTransaction() {
        if (entityManager.getTransaction().isActive()) {
            entityManager.getTransaction().commit();
        }
    }
}
