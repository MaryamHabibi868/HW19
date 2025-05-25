package ir.maktabhw19.repository;

import ir.maktabhw19.domains.Answer;
import ir.maktabhw19.repository.base.SimpleJpaRepository;
import jakarta.persistence.EntityManager;

public class AnswerRepositoryImpl
        extends SimpleJpaRepository<Answer, Long>
        implements AnswerRepository {

    public AnswerRepositoryImpl(EntityManager entityManager) {
        super(entityManager);
    }

    @Override
    protected Class<Answer> getDomainClass() {
        return Answer.class;
    }
}
