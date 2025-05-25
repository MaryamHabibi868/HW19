package ir.maktabhw19.service;

import ir.maktabhw19.domains.AnswerToMCQ;
import ir.maktabhw19.repository.AnswerToMCQRepository;
import ir.maktabhw19.service.base.BaseServiceImpl;

public class AnswerToMCQServiceImpl
        extends BaseServiceImpl<AnswerToMCQ, Long, AnswerToMCQRepository>
        implements AnswerToMCQService {
    public AnswerToMCQServiceImpl(AnswerToMCQRepository repository) {
        super(repository);
    }
}
