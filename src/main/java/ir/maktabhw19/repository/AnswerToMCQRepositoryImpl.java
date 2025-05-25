package ir.maktabhw19.repository;

import ir.maktabhw19.domains.AnswerToMCQ;
import ir.maktabhw19.repository.base.SimpleJpaRepository;
import jakarta.persistence.EntityManager;

public class AnswerToMCQRepositoryImpl
        extends SimpleJpaRepository<AnswerToMCQ, Long>
        implements AnswerToMCQRepository {
    public AnswerToMCQRepositoryImpl(EntityManager entityManager) {
        super(entityManager);
    }

    @Override
    protected Class<AnswerToMCQ> getDomainClass() {
        return AnswerToMCQ.class;
    }
}
