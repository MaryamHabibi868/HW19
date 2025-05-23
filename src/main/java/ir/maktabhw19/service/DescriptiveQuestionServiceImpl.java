package ir.maktabhw19.service;

import ir.maktabhw19.domains.DescriptiveQuestion;
import ir.maktabhw19.repository.DescriptiveQuestionRepository;
import ir.maktabhw19.service.base.BaseServiceImpl;

public class DescriptiveQuestionServiceImpl
        extends BaseServiceImpl<DescriptiveQuestion, Long,
        DescriptiveQuestionRepository>
        implements DescriptiveQuestionService {

    public DescriptiveQuestionServiceImpl(DescriptiveQuestionRepository repository) {
        super(repository);
    }
}
