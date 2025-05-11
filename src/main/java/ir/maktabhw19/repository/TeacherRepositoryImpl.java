package ir.maktabhw19.repository;


import ir.maktabhw19.domains.Teacher;
import ir.maktabhw19.repository.base.BaseUserRepositoryImpl;
import jakarta.persistence.EntityManager;

public class TeacherRepositoryImpl
        extends BaseUserRepositoryImpl<Teacher>
        implements TeacherRepository {

    protected TeacherRepositoryImpl(EntityManager entityManager) {
        super(entityManager);
    }

    @Override
    protected Class<Teacher> getDomainClass() {
        return Teacher.class;
    }
}
