package ir.maktabhw19.service;

import ir.maktabhw19.domains.Answer;
import ir.maktabhw19.domains.Course;
import ir.maktabhw19.repository.AnswerRepository;
import ir.maktabhw19.service.base.BaseServiceImpl;

public class AnswerServiceImpl
        extends BaseServiceImpl<Answer, Long, AnswerRepository>
        implements AnswerService {
    public AnswerServiceImpl(AnswerRepository repository) {
        super(repository);
    }

    public void calculateGivenScore(Long studentId, Long questionId,
                                    Long answerId, Double givenScore) {}
}
