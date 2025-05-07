package ir.maktabhw19.service.base;

import ir.maktabhw19.domains.base.BaseEntity;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface BaseUserService
        <T extends BaseEntity<ID> , ID > {
    T save(T entity);
    List<T> saveAll(Collection<T> entities);
    Optional<T> findById(ID id);
    List<T> findAll();
    List<T> findAllById(Iterable<ID> ids);
    Long countAll();
    void deleteById(ID id);
    void deleteAllById(Iterable<ID> ids);
    boolean existsById(ID id);
}
