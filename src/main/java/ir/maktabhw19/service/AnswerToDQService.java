package ir.maktabhw19.service;

import ir.maktabhw19.domains.AnswerToDQ;
import ir.maktabhw19.service.base.BaseService;

public interface AnswerToDQService
        extends BaseService<AnswerToDQ, Long> {

    void calculateGivenScore(Long studentId, Long questionId,
                             Long answerToDQId, Double givenScore);
}
