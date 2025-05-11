package ir.maktabhw19.repository;

import ir.maktabhw19.domains.Student;
import ir.maktabhw19.repository.base.BaseUserRepositoryImpl;
import jakarta.persistence.EntityManager;

public class StudentRepositoryImpl
        extends BaseUserRepositoryImpl<Student>
        implements StudentRepository {

    public StudentRepositoryImpl(EntityManager entityManager) {
        super(entityManager);
    }

    @Override
    protected Class<Student> getDomainClass() {
        return Student.class;
    }


}
