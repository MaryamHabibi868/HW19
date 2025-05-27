package ir.maktabhw19.service;

import ir.maktabhw19.domains.AnswerToDQ;
import ir.maktabhw19.repository.AnswerToDQRepository;
import ir.maktabhw19.service.base.BaseServiceImpl;

public class AnswerToDQServiceImpl
        extends BaseServiceImpl<AnswerToDQ, Long, AnswerToDQRepository>
        implements AnswerToDQService{
    public AnswerToDQServiceImpl(AnswerToDQRepository repository,
                                 StudentService studentService, DescriptiveQuestionService descriptiveQuestionService) {
        super(repository);
        this.studentService = studentService;
        this.descriptiveQuestionService = descriptiveQuestionService;
    }

    private final StudentService studentService;
    private final DescriptiveQuestionService descriptiveQuestionService;

    public void answerToDQ(Long studentId, Long questionId, String answer) {
        repository.beginTransaction();
        if (studentService.findById(studentId).isEmpty()){
            throw new RuntimeException("Student not found");
        }
        if (descriptiveQuestionService.findById(questionId).isEmpty()){
            throw new RuntimeException("Question not found");
        }
        AnswerToDQ answerToDQ = new AnswerToDQ();
        answerToDQ.setAnswer(answer);
        repository.save(answerToDQ);
        repository.commitTransaction();
    }
}
