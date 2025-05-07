package ir.maktabhw19.repository;

import ir.maktabhw19.domains.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;

import java.util.List;
import java.util.Optional;

public abstract class BaseUserRepositoryImpl<T extends User>
        extends SimpleJpaRepository<T, Long>
        implements BaseUserRepository<T> {

    protected BaseUserRepositoryImpl(EntityManager entityManager) {
        super(entityManager);
    }

    @Override
    public Optional<T> findByUsername(String username) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<T> query = cb.createQuery(getDomainClass());
        Root<T> from = query.from(getDomainClass());
        query.select(from);
        query.where(cb.equal(from.get("username"), username));
        return Optional.ofNullable(entityManager.createQuery(query).getSingleResult());
    }

    @Override
    public Optional<T> findByUsernameAndPassword(String username, String password) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<T> query = cb.createQuery(getDomainClass());
        Root<T> from = query.from(getDomainClass());
        query.select(from);
        query.where(
                cb.and(
                        cb.equal(from.get("username"), username),
                        cb.equal(from.get("password"), password)
                )
        );
        return Optional.ofNullable(
                entityManager.createQuery(query).getSingleResult());
    }
}
