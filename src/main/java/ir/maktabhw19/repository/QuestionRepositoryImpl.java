package ir.maktabhw19.repository;

import ir.maktabhw19.domains.Course;
import ir.maktabhw19.domains.Question;
import ir.maktabhw19.repository.base.SimpleJpaRepository;
import jakarta.persistence.EntityManager;

public class QuestionRepositoryImpl
        extends SimpleJpaRepository<Question, Long>
        implements QuestionRepository {

    public QuestionRepositoryImpl(EntityManager entityManager) {
        super(entityManager);
    }

    @Override
    protected Class<Question> getDomainClass() {
        return Question.class;
    }
}
