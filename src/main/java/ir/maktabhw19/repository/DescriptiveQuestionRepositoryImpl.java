package ir.maktabhw19.repository;


import ir.maktabhw19.domains.DescriptiveQuestion;
import ir.maktabhw19.repository.base.SimpleJpaRepository;
import jakarta.persistence.EntityManager;

public class DescriptiveQuestionRepositoryImpl
        extends SimpleJpaRepository<DescriptiveQuestion, Long>
        implements DescriptiveQuestionRepository {

    public DescriptiveQuestionRepositoryImpl(EntityManager entityManager) {
        super(entityManager);
    }

    @Override
    protected Class<DescriptiveQuestion> getDomainClass() {
        return DescriptiveQuestion.class;
    }
}
