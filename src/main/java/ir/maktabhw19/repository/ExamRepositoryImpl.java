package ir.maktabhw19.repository;

import ir.maktabhw19.domains.Course;
import ir.maktabhw19.domains.Exam;
import ir.maktabhw19.repository.base.SimpleJpaRepository;
import jakarta.persistence.EntityManager;

public class ExamRepositoryImpl
        extends SimpleJpaRepository<Exam, Long>
        implements ExamRepository {

    public ExamRepositoryImpl(EntityManager entityManager) {
        super(entityManager);
    }

    @Override
    protected Class<Exam> getDomainClass() {
        return Exam.class;
    }
}
