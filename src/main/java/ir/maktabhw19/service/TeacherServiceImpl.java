package ir.maktabhw19.service;

import ir.maktabhw19.domains.*;
import ir.maktabhw19.repository.TeacherRepository;
import ir.maktabhw19.service.base.BaseServiceImpl;

import java.time.LocalDateTime;
import java.util.*;

public class TeacherServiceImpl
        extends BaseServiceImpl<Teacher, Long, TeacherRepository>
        implements TeacherService {

    public TeacherServiceImpl(TeacherRepository repository,
                              ExamService examService,
                              DescriptiveQuestionService descriptiveQuestionService,
                              MultipleChoiceQuestionService multipleChoiceQuestionService,
                              QuestionService questionService, CourseService courseService) {
        super(repository);
        this.examService = examService;
        this.descriptiveQuestionService = descriptiveQuestionService;
        this.multipleChoiceQuestionService = multipleChoiceQuestionService;
        this.questionService = questionService;
        this.courseService = courseService;
    }

    private final ExamService examService;
    private final DescriptiveQuestionService descriptiveQuestionService;
    private final MultipleChoiceQuestionService multipleChoiceQuestionService;
    private final QuestionService questionService;
    private final CourseService courseService;

    @Override
    public Optional<Teacher> findTeacherByUserName(String userName) {
        if (repository.findByUsername(userName).isEmpty()) {
            throw new RuntimeException("Teacher not found");
        }
        return repository.findByUsername(userName);
    }


    @Override
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

    @Override
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

    public void addQuestionToExamByTeacherFromBankQuestion(
            Long teacherId, Long examId, Long questionId, Double score) {
        repository.beginTransaction();
        if (repository.findById(teacherId).isEmpty()) {
            throw new RuntimeException("Teacher not found");
        }
        if (repository.findById(examId).isEmpty()) {
            throw new RuntimeException("Exam not found");
        }
        if (repository.findById(questionId).isEmpty()) {
            throw new RuntimeException("Question not found");
        }
        Question question = questionService.findById(questionId).get();
        questionService.save(question);
        Exam exam = examService.findById(examId).get();
        exam.setScore(exam.getScore() + score);
        examService.save(exam);
        repository.commitTransaction();
        System.out.println("Question added successfully");
    }

    public void addDescriptiveQuestionToExamByTeacher(
            Long teacherId, Long examId,
            String title, String statement,
            Double score) {
        repository.beginTransaction();
        repository.beginTransaction();
        if (repository.findById(teacherId).isEmpty()) {
            throw new RuntimeException("Teacher not found");
        }
        if (repository.findById(examId).isEmpty()) {
            throw new RuntimeException("Exam not found");
        }
        Question question = new Question();
        question.setQuestionTitle(title);
        question.setQuestionStatement(statement);
        questionService.save(question);
        Exam exam = examService.findById(examId).get();
        exam.setScore(exam.getScore() + score);
        examService.save(exam);
        repository.commitTransaction();
        System.out.println("Descriptive Question added successfully");
    }

    public void addMCQuestionToExamByTeacher(
            Long teacherId, Long examId,
            String title, String statement,
            List<String> options, Integer indexOfCorrectAnswer,
            Double score) {
        repository.beginTransaction();
        repository.beginTransaction();
        if (repository.findById(teacherId).isEmpty()) {
            throw new RuntimeException("Teacher not found");
        }
        if (repository.findById(examId).isEmpty()) {
            throw new RuntimeException("Exam not found");
        }
        MultipleChoiceQuestion question = new MultipleChoiceQuestion();
        question.setQuestionTitle(title);
        question.setQuestionStatement(statement);
        question.setOptions(options);
        question.setCorrectOptionIndex(indexOfCorrectAnswer);
        questionService.save(question);
        Exam exam = examService.findById(examId).get();
        exam.setScore(exam.getScore() + score);
        examService.save(exam);
        repository.commitTransaction();
        System.out.println("Multiple Choice Question added successfully");
    }

    @Override
    public void runExam(Long examId) {
        repository.beginTransaction();
        Exam exam = examService.findById(examId).get();
        if (exam.getStartDate().isBefore(LocalDateTime.now())) {
            throw new RuntimeException("Please start in " + exam.getStartDate());
        }
        if (exam.getEndDate().isAfter(LocalDateTime.now())) {
            throw new RuntimeException("Exam have finished in " + exam.getEndDate());
        }
        System.out.println("Exam started");
        System.out.println("Exam will be finished at " + exam.getEndDate());
        System.out.println(exam.getQuestions());
    }

    @Override
    public void printAllCoursesByTeacherId(Long teacherId) {
        if(repository.findById(teacherId).isEmpty()) {
            throw new RuntimeException("Teacher not found");
        }
        Teacher teacher = repository.findById(teacherId).get();
        System.out.println("This is the list of courses for the teacher");
        System.out.println(teacher.getCourses());
    }

    @Override
    public void printAllExamsByCourseId(Long courseId) {
        if(courseService.findById(courseId).isEmpty()) {
            throw new RuntimeException("Course not found");
        }
        Course course = courseService.findById(courseId).get();
        System.out.println("This is the list of exams for the course");
        System.out.println(course.getExams());
    }
    /*public void addDescriptiveQuestionToExamByTeacher(
            Long teacherId, Long examId,
            Long descriptiveQuestionId,
            Double defaultScore) {
        Double questionDefaultScore = 0.0;
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
        descriptiveQuestionService.save(descriptiveQuestion);
        Exam exam = examService.findById(examId).get();
        exam.setScore(defaultScore);
        examService.save(exam);
        repository.commitTransaction();
        System.out.println("Add descriptive question to exam successfully");
    }*/

   /* public void addMultipleChoiceQuestionToExamByTeacher(
            Long teacherId, Long examId,
            Long multipleChoiceQuestionId) {
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
        multipleChoiceQuestionService.save(multipleChoiceQuestion);
        Exam exam = examService.findById(examId).get();
        exam.setQuestions((Set<Question>) multipleChoiceQuestion);
        examService.save(exam);
        repository.commitTransaction();
        System.out.println("Add multiple choice question to exam successfully");
    }*/


    /*public void addDescriptiveQuestionsByTeacher(
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
    }*/

}
