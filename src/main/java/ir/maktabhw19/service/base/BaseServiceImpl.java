package ir.maktabhw19.service.base;

import ir.maktabhw19.domains.base.BaseEntity;
import ir.maktabhw19.repository.base.CrudRepository;
import lombok.RequiredArgsConstructor;


import java.util.Collection;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public class BaseServiceImpl
        <T extends BaseEntity<ID>, ID,
                R extends CrudRepository<T, ID>>
        implements BaseService<T, ID> {

    protected final R repository;

    @Override
    public T save(T entity) {
        repository.beginTransaction();
        repository.save(entity);
        repository.commitTransaction();
        return entity;
    }

    @Override
    public List<T> saveAll(Collection<T> entities) {
        repository.beginTransaction();
        List<T> entitiesList = repository.saveAll(entities);
        repository.commitTransaction();
        return entitiesList;
    }

    @Override
    public Optional<T> findById(ID id) {
        return repository.findById(id);
    }

    @Override
    public List<T> findAll() {
        return repository.findAll();
    }

    @Override
    public List<T> findAllById(Iterable<ID> ids) {
        return repository.findAllById(ids);
    }

    @Override
    public Long countAll() {
        return repository.countAll();
    }

    @Override
    public void deleteById(ID id) {
        repository.beginTransaction();
        repository.deleteById(id);
        repository.commitTransaction();
    }

    @Override
    public void deleteAllById(Iterable<ID> ids) {
        repository.beginTransaction();
        repository.deleteAllById(ids);
        repository.commitTransaction();
    }

    @Override
    public boolean existsById(ID id) {
        return repository.existsById(id);
    }
}
