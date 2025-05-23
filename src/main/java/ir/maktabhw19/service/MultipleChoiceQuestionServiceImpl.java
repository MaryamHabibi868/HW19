package ir.maktabhw19.service;

import ir.maktabhw19.domains.MultipleChoiceQuestion;
import ir.maktabhw19.repository.MultipleChoiceQuestionRepository;
import ir.maktabhw19.service.base.BaseServiceImpl;

public class MultipleChoiceQuestionServiceImpl
        extends BaseServiceImpl<MultipleChoiceQuestion, Long,
        MultipleChoiceQuestionRepository>
        implements MultipleChoiceQuestionService {
    public MultipleChoiceQuestionServiceImpl(MultipleChoiceQuestionRepository repository) {
        super(repository);
    }
}
