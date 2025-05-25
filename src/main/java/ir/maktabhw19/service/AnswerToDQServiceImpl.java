package ir.maktabhw19.service;

import ir.maktabhw19.domains.AnswerToDQ;
import ir.maktabhw19.repository.AnswerToDQRepository;
import ir.maktabhw19.service.base.BaseServiceImpl;

public class AnswerToDQServiceImpl
        extends BaseServiceImpl<AnswerToDQ, Long, AnswerToDQRepository>
        implements AnswerToDQService{
    public AnswerToDQServiceImpl(AnswerToDQRepository repository) {
        super(repository);
    }
}
