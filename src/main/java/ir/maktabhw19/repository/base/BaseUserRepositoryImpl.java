package ir.maktabhw19.repository.base;

import ir.maktabhw19.domains.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;

import java.util.Optional;

public abstract class BaseUserRepositoryImpl<T extends User>
        extends SimpleJpaRepository<T, Long>
        implements BaseUserRepository<T> {

    protected BaseUserRepositoryImpl(EntityManager entityManager) {
        super(entityManager);
    }

    @Override
    public Optional<T> findByUserName(String userName) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<T> query = cb.createQuery(getDomainClass());
        Root<T> from = query.from(getDomainClass());
        query.select(from);
        query.where(cb.equal(from.get("userName"), userName));
        return Optional.ofNullable(entityManager.createQuery(query).getSingleResult());
    }

    @Override
    public Optional<T> findByUserNameAndPassword(String userName, String password) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<T> query = cb.createQuery(getDomainClass());
        Root<T> from = query.from(getDomainClass());
        query.select(from);
        query.where(
                cb.and(
                        cb.equal(from.get("userName"), userName),
                        cb.equal(from.get("password"), password)
                )
        );
        return Optional.ofNullable(
                entityManager.createQuery(query).getSingleResult());
    }
}
