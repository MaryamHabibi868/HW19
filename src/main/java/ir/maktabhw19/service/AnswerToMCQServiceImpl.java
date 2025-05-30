package ir.maktabhw19.service;

import ir.maktabhw19.domains.*;
import ir.maktabhw19.repository.AnswerToMCQRepository;
import ir.maktabhw19.service.base.BaseServiceImpl;

import java.util.Set;

public class AnswerToMCQServiceImpl
        extends BaseServiceImpl<AnswerToMCQ, Long, AnswerToMCQRepository>
        implements AnswerToMCQService {
    public AnswerToMCQServiceImpl(AnswerToMCQRepository repository,
                                  StudentService studentService,
                                  MultipleChoiceQuestionService multipleChoiceQuestionService, ExamService examService) {
        super(repository);
        this.studentService = studentService;
        this.multipleChoiceQuestionService = multipleChoiceQuestionService;
        this.examService = examService;
    }

    private final StudentService studentService;
    private final MultipleChoiceQuestionService multipleChoiceQuestionService;
    private final ExamService examService;

    @Override
    public void answerToMCQ(Long studentId, Long examId,
                            Long questionId,
                            Integer selectedOption) {
        repository.beginTransaction();
        if (studentService.findById(studentId).isEmpty()){
            throw new RuntimeException("Student not found");
        }
        if (multipleChoiceQuestionService.findById(questionId).isEmpty()){
            throw new RuntimeException("Question not found");
        }
        if(examService.findById(examId).isEmpty()){
            throw new RuntimeException("Exam not found");
        }
        /*if (!examService.findById(examId).get().getQuestions().contains(multipleChoiceQuestionService.findById(questionId).get())) {
            throw new RuntimeException("This question is not part of the selected exam.");
        }*/
        AnswerToMCQ answerToMCQ = new AnswerToMCQ();
        answerToMCQ.setStudent(studentService.findById(studentId).get());
        answerToMCQ.setQuestion(multipleChoiceQuestionService.findById(questionId).get());
        answerToMCQ.setSelectedOption(selectedOption);
        answerToMCQ.setQuestion(multipleChoiceQuestionService.findById(questionId).get());
        answerToMCQ.setStudent(studentService.findById(studentId).get());
        answerToMCQ.calculateGivenScore();
        repository.save(answerToMCQ);
        multipleChoiceQuestionService.save(multipleChoiceQuestionService.findById(questionId).get());
        repository.commitTransaction();
    }

    @Override
    public void calculateGivenScore(Long studentId, Long questionId,
                                    Long answerToDQId, Double givenScore) {
        repository.beginTransaction();
        if (studentService.findById(studentId).isEmpty()){
            throw new RuntimeException("Student not found");
        }
        if (multipleChoiceQuestionService.findById(questionId).isEmpty()){
            throw new RuntimeException("Question not found");
        }
        if (repository.findById(answerToDQId).isEmpty()){
            throw new RuntimeException("Answer not found");
        }
        AnswerToMCQ answerToMCQ = new AnswerToMCQ();
        answerToMCQ.setGivenScore(givenScore);
        repository.save(answerToMCQ);
        MultipleChoiceQuestion multipleChoiceQuestion = new MultipleChoiceQuestion();
        multipleChoiceQuestion.setAnswers((Set<Answer>) answerToMCQ);
        multipleChoiceQuestionService.save(multipleChoiceQuestion);
        repository.commitTransaction();
    }
}
