package ir.maktabhw19.service;

import ir.maktabhw19.domains.AnswerToMCQ;
import ir.maktabhw19.service.base.BaseService;

public interface AnswerToMCQService
        extends BaseService<AnswerToMCQ, Long> {

    void calculateGivenScore(Long studentId, Long questionId,
                             Long answerToDQId, Double givenScore);
}
