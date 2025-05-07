package ir.maktabhw19.repository;

import ir.maktabhw19.domains.base.BaseEntity;
import jakarta.persistence.EntityManager;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface CrudRepository
        <T extends BaseEntity<ID> , ID> {

    T save(T entity);
    List<T> saveAll(Collection<T> entities);
    Optional<T> findById(ID id);
    List<T> findAll();
    List<T> findAllById(Iterable<ID> ids);
    Long countAll();
    void deleteById(ID id);
    void deleteAllById(Iterable<ID> ids);
    boolean existsById(ID id);
    void beginTransaction();
    void commitTransaction();

}
