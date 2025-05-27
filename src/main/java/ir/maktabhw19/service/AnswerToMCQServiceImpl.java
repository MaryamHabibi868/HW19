package ir.maktabhw19.service;

import ir.maktabhw19.domains.AnswerToMCQ;
import ir.maktabhw19.repository.AnswerToMCQRepository;
import ir.maktabhw19.service.base.BaseServiceImpl;

public class AnswerToMCQServiceImpl
        extends BaseServiceImpl<AnswerToMCQ, Long, AnswerToMCQRepository>
        implements AnswerToMCQService {
    public AnswerToMCQServiceImpl(AnswerToMCQRepository repository,
                                  StudentService studentService,
                                  MultipleChoiceQuestionService multipleChoiceQuestionService) {
        super(repository);
        this.studentService = studentService;
        this.multipleChoiceQuestionService = multipleChoiceQuestionService;
    }

    private final StudentService studentService;
    private final MultipleChoiceQuestionService multipleChoiceQuestionService;

    public void answerToMCQ(Long studentId, Long questionId,
                            Integer selectedOption) {
        repository.beginTransaction();
        if (studentService.findById(studentId).isEmpty()){
            throw new RuntimeException("Student not found");
        }
        if (multipleChoiceQuestionService.findById(questionId).isEmpty()){
            throw new RuntimeException("Question not found");
        }
        AnswerToMCQ answerToMCQ = new AnswerToMCQ();
        answerToMCQ.setSelectedOption(selectedOption);
        answerToMCQ.calculateGivenScore();
        repository.save(answerToMCQ);
        repository.commitTransaction();
    }
}
