package ir.maktabhw19.repository;

import ir.maktabhw19.domains.AnswerToDQ;
import ir.maktabhw19.repository.base.SimpleJpaRepository;
import jakarta.persistence.EntityManager;

public class AnswerToDQRepositoryImpl
        extends SimpleJpaRepository<AnswerToDQ, Long>
        implements AnswerToDQRepository{

    public AnswerToDQRepositoryImpl(EntityManager entityManager) {
        super(entityManager);
    }

    @Override
    protected Class<AnswerToDQ> getDomainClass() {
        return AnswerToDQ.class;
    }
}
