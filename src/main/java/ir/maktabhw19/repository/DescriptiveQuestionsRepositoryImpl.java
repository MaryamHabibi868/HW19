package ir.maktabhw19.repository;


import ir.maktabhw19.domains.DescriptiveQuestions;
import ir.maktabhw19.repository.base.SimpleJpaRepository;
import jakarta.persistence.EntityManager;

public class DescriptiveQuestionsRepositoryImpl
        extends SimpleJpaRepository<DescriptiveQuestions, Long>
        implements DescriptiveQuestionsRepository {

    public DescriptiveQuestionsRepositoryImpl(EntityManager entityManager) {
        super(entityManager);
    }

    @Override
    protected Class<DescriptiveQuestions> getDomainClass() {
        return DescriptiveQuestions.class;
    }
}
