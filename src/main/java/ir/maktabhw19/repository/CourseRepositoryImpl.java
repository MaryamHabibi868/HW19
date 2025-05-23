package ir.maktabhw19.repository;

import ir.maktabhw19.domains.Course;
import ir.maktabhw19.repository.base.SimpleJpaRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;

import java.util.Optional;

public class CourseRepositoryImpl
        extends SimpleJpaRepository <Course, Long>
        implements CourseRepository {

    public CourseRepositoryImpl(EntityManager entityManager) {
        super(entityManager);
    }

    @Override
    protected Class<Course> getDomainClass() {
        return Course.class;
    }

    @Override
    public Optional<Course> findByTitle(String title) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Course> query = cb.createQuery(getDomainClass());
        Root<Course> from = query.from(getDomainClass());
        query.select(from);
        query.where(cb.equal(from.get("title"), title));
        return Optional.ofNullable(entityManager.createQuery(query).getSingleResult());
    }
}
