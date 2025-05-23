package ir.maktabhw19.service;

import ir.maktabhw19.domains.Question;
import ir.maktabhw19.repository.QuestionRepository;
import ir.maktabhw19.service.base.BaseServiceImpl;

public class QuestionServiceImpl
        extends BaseServiceImpl<Question, Long, QuestionRepository>
        implements QuestionService {

    public QuestionServiceImpl(QuestionRepository repository) {
        super(repository);
    }
}
