package ir.maktabhw19.repository;

import ir.maktabhw19.domains.Course;
import ir.maktabhw19.domains.MultipleChoiceQuestion;
import ir.maktabhw19.repository.base.SimpleJpaRepository;
import jakarta.persistence.EntityManager;

public class MultipleChoiceQuestionRepositoryImpl
        extends SimpleJpaRepository<MultipleChoiceQuestion, Long>
        implements MultipleChoiceQuestionRepository {

    public MultipleChoiceQuestionRepositoryImpl(EntityManager entityManager) {
        super(entityManager);
    }

    @Override
    protected Class<MultipleChoiceQuestion> getDomainClass() {
        return MultipleChoiceQuestion.class;
    }
}
