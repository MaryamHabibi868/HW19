package ir.maktabhw19.service;

import ir.maktabhw19.domains.*;
import ir.maktabhw19.repository.TeacherRepository;
import ir.maktabhw19.service.base.BaseServiceImpl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

public class TeacherServiceImpl
        extends BaseServiceImpl<Teacher, Long, TeacherRepository>
        implements TeacherService {

    public TeacherServiceImpl(TeacherRepository repository,
                              ExamService examService,
                              DescriptiveQuestionService descriptiveQuestionService,
                              MultipleChoiceQuestionService multipleChoiceQuestionService,
                              QuestionService questionService) {
        super(repository);
        this.examService = examService;
        this.descriptiveQuestionService = descriptiveQuestionService;
        this.multipleChoiceQuestionService = multipleChoiceQuestionService;
        this.questionService = questionService;
    }

    private final ExamService examService;
    private final DescriptiveQuestionService descriptiveQuestionService;
    private final MultipleChoiceQuestionService multipleChoiceQuestionService;
    private final QuestionService questionService;

    @Override
    public Optional<Teacher> findTeacherByUserName(String userName) {
        if (repository.findByUsername(userName).isEmpty()) {
            throw new RuntimeException("Teacher not found");
        }
        return repository.findByUsername(userName);
    }


    public void registerTeacher(String firstName, String lastName,
                                String userName, String password) {
        repository.beginTransaction();
        Teacher teacher = new Teacher();
        teacher.setFirstName(firstName);
        teacher.setLastName(lastName);
        teacher.setUserName(userName);
        teacher.setPassword(password);
        teacher.setRegistrationConfirmation(RegistrationConfirmation.PENDING);
        repository.save(teacher);
        repository.commitTransaction();
        System.out.println("Your registration is pending. " +
                "Manager should approve your registration ");
    }

    public void loginTeacher(String username, String password) {
        repository.beginTransaction();
        Teacher teacher = new Teacher();
        Optional<Teacher> foundTeacher = repository.findByUsername(username);
        if (foundTeacher.isPresent()) {
            if (foundTeacher.get().getPassword().equals(password)) {
                System.out.println("Student logged in successfully");
            } else {
                System.out.println("Wrong password");
            }
        } else {
            System.out.println("userName not found");
        }
    }

    public void addDescriptiveQuestionToExamByTeacher(
            Long teacherId, Long examId,
            Long descriptiveQuestionId,
            Double defaultScore) {
        repository.beginTransaction();
        if (repository.findById(teacherId).isEmpty()) {
            throw new RuntimeException("Teacher not found");
        }
        if (examService.findById(examId).isEmpty()) {
            throw new RuntimeException("Exam not found");
        }
        if (descriptiveQuestionService.findById(descriptiveQuestionId).isEmpty()) {
            throw new RuntimeException("Descriptive question not found");
        }
        DescriptiveQuestion descriptiveQuestion = new DescriptiveQuestion();
        descriptiveQuestion.setDefaultScore(defaultScore);
        descriptiveQuestionService.save(descriptiveQuestion);
        Exam exam = examService.findById(examId).get();
        exam.setQuestions((Set<Question>) descriptiveQuestion);
        examService.save(exam);
        repository.commitTransaction();
        System.out.println("Add descriptive question to exam successfully");
    }

    public void addMultipleChoiceQuestionToExamByTeacher(
            Long teacherId, Long examId,
            Long multipleChoiceQuestionId,
            Double defaultScore) {
        repository.beginTransaction();
        if (repository.findById(teacherId).isEmpty()) {
            throw new RuntimeException("Teacher not found");
        }
        if (examService.findById(examId).isEmpty()) {
            throw new RuntimeException("Exam not found");
        }
        if (multipleChoiceQuestionService.findById(multipleChoiceQuestionId).isEmpty()) {
            throw new RuntimeException("Multiple Choice question not found");
        }
        MultipleChoiceQuestion multipleChoiceQuestion = new MultipleChoiceQuestion();
        multipleChoiceQuestion.setDefaultScore(defaultScore);
        multipleChoiceQuestionService.save(multipleChoiceQuestion);
        Exam exam = examService.findById(examId).get();
        exam.setQuestions((Set<Question>) multipleChoiceQuestion);
        examService.save(exam);
        repository.commitTransaction();
        System.out.println("Add multiple choice question to exam successfully");
    }

    public void addExamByTeacher(Long teacherId, LocalDateTime startDate,
                                 LocalDateTime endDate) {
        repository.beginTransaction();
        if (repository.findById(teacherId).isEmpty()) {
            throw new RuntimeException("Teacher not found");
        }
        Exam exam = new Exam();
        exam.setStartDate(startDate);
        exam.setEndDate(endDate);
        examService.save(exam);
        repository.commitTransaction();
    }

    public void addDescriptiveQuestionsByTeacher(
            Long teacherID, String questionTitle,
            String questionStatement) {
        repository.beginTransaction();
        if (repository.findById(teacherID).isEmpty()) {
            throw new RuntimeException("Teacher not found");
        }
        DescriptiveQuestion question = new DescriptiveQuestion();
        question.setQuestionTitle(questionTitle);
        question.setQuestionStatement(questionStatement);
        descriptiveQuestionService.save(question);
        repository.commitTransaction();
        System.out.println("Descriptive Question added successfully");
    }

    public void addMultipleChoiceQuestionsByTeacher(
            Long teacherID, String questionTitle,
            String questionStatement, List<String> options,
            Integer correctOptionIndex) {
        repository.beginTransaction();
        if (repository.findById(teacherID).isEmpty()) {
            throw new RuntimeException("Teacher not found");
        }
        MultipleChoiceQuestion question = new MultipleChoiceQuestion();
        question.setQuestionTitle(questionTitle);
        question.setQuestionStatement(questionStatement);
        List<String> optionsList = new ArrayList<>();
        optionsList.addAll(options);
        question.setOptions(optionsList);
        question.setCorrectOptionIndex(correctOptionIndex);
        multipleChoiceQuestionService.save(question);
        repository.commitTransaction();
        System.out.println("Multiple Choice Question added successfully");
    }
}
