package ir.maktabhw19.repository;

import ir.maktabhw19.domains.Manager;
import ir.maktabhw19.domains.Student;
import ir.maktabhw19.domains.Teacher;
import ir.maktabhw19.repository.base.BaseUserRepositoryImpl;
import jakarta.persistence.EntityManager;

public class ManagerRepositoryImpl
        extends BaseUserRepositoryImpl<Manager>
        implements ManagerRepository{

    protected ManagerRepositoryImpl(EntityManager entityManager) {
        super(entityManager);
    }

    @Override
    protected Class<Manager> getDomainClass() {
        return Manager.class;
    }
}
