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
                              QuestionService questionService,
                              CourseService courseService,
                              StudentService studentService,
                              AnswerToMCQService answerToMCQService,
                              AnswerToDQService answerToDQService) {
        super(repository);
        this.examService = examService;
        this.descriptiveQuestionService = descriptiveQuestionService;
        this.multipleChoiceQuestionService = multipleChoiceQuestionService;
        this.questionService = questionService;
        this.courseService = courseService;
        this.studentService = studentService;
        this.answerToMCQService = answerToMCQService;
        this.answerToDQService = answerToDQService;
    }

    private final ExamService examService;
    private final DescriptiveQuestionService descriptiveQuestionService;
    private final MultipleChoiceQuestionService multipleChoiceQuestionService;
    private final QuestionService questionService;
    private final CourseService courseService;
    private final StudentService studentService;
    private final AnswerToMCQService answerToMCQService;
    private final AnswerToDQService answerToDQService;

    @Override
    public Optional<Teacher> findTeacherByUserName(String userName) {
        if (repository.findByUserName(userName).isEmpty()) {
            throw new RuntimeException("Teacher not found");
        }
        return repository.findByUserName(userName);
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
        Optional<Teacher> foundTeacher = repository.findByUserName(username);
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
        /*repository.beginTransaction();*/
        if (repository.findById(teacherId).isEmpty()) {
            throw new RuntimeException("Teacher not found");
        }
        Exam exam = new Exam();
        exam.setStartDate(startDate);
        exam.setEndDate(endDate);
        examService.save(exam);
        /*repository.commitTransaction();*/
    }

    public void addQuestionToExamByTeacherFromBankQuestion(
            Long teacherId, Long examId, Long questionId, Double score) {
        /*repository.beginTransaction();*/
        if (repository.findById(teacherId).isEmpty()) {
            throw new RuntimeException("Teacher not found");
        }
        if (examService.findById(examId).isEmpty()) {
            throw new RuntimeException("Exam not found");
        }
        if (questionService.findById(questionId).isEmpty()) {
            throw new RuntimeException("Question not found");
        }
        Question question = questionService.findById(questionId).get();
        questionService.save(question);
        Exam exam = examService.findById(examId).get();
        Double currentScore = exam.getScore() != null ? exam.getScore() : 0.0;
        exam.setScore(currentScore + score);
        examService.save(exam);
        /*repository.commitTransaction();*/
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
        if (repository.findById(teacherId).isEmpty()) {
            throw new RuntimeException("Teacher not found");
        }
        Teacher teacher = repository.findById(teacherId).get();
        System.out.println("This is the list of courses for the teacher");
        System.out.println(teacher.getCourses());
    }

    @Override
    public void printAllExamsByCourseId(Long courseId) {
        if (courseService.findById(courseId).isEmpty()) {
            throw new RuntimeException("Course not found");
        }
        Course course = courseService.findById(courseId).get();
        System.out.println("This is the list of exams for the course");
        System.out.println(course.getExams());
    }

    @Override
    public void printAllExamsInCourseByTeacherId(Long teacherId,
                                                 Long courseId) {
        repository.beginTransaction();
        if (repository.findById(teacherId).isEmpty()) {
            throw new RuntimeException("Teacher not found");
        }
        if (courseService.findById(courseId).isEmpty()) {
            throw new RuntimeException("Course not found");
        }
        Course course = courseService.findById(courseId).get();
        List<Exam> examsOfThisTeacher = course.getExams().stream()
                .filter(exam -> exam.getCourse().getTeacher().getId().equals(teacherId))
                .toList();
        System.out.println("This is the list of exams for the teacher");
        System.out.println(examsOfThisTeacher);
        repository.commitTransaction();
    }

    @Override
    public void removeExamFromCourseByTeacherId(Long teacherId,
                                                Long examId) {
        repository.beginTransaction();
        if (repository.findById(teacherId).isEmpty()) {
            throw new RuntimeException("Teacher not found");
        }
        if (examService.findById(examId).isEmpty()) {
            throw new RuntimeException("Exam not found");
        }
        Exam exam = examService.findById(examId).get();
        if (exam.getCourse().getTeacher().getId().equals(teacherId)) {
            examService.deleteById(examId);
            examService.save(exam);
            Course course = courseService.findById(exam.getCourse().getId()).get();
            course.getExams().remove(exam);
            courseService.save(course);
            repository.commitTransaction();
            System.out.println("Exams removed successfully");
        } else {
            throw new RuntimeException("You cannot delete this exam because you didn't create it.");
        }
    }

    @Override
    public void printAllQuestionsByTeacherId(Long teacherId, Long courseId) {
        repository.beginTransaction();
        if (repository.findById(teacherId).isEmpty()) {
            throw new RuntimeException("Teacher not found");
        }
        if (courseService.findById(courseId).isEmpty()) {
            throw new RuntimeException("Course not found");
        }
        Course course = courseService.findById(courseId).get();
        System.out.println("This is the list of questions for this course by the teacher");
        course.getExams().forEach(exam -> {
            if (exam.getCourse().getTeacher().getId().equals(teacherId)) {
                exam.getQuestions().forEach(System.out::println);
            }
        });
        repository.commitTransaction();
    }

    @Override
    public void printAllStudentInExamByExamId(Long examId) {
        repository.beginTransaction();
        if (examService.findById(examId).isEmpty()) {
            throw new RuntimeException("Exam not found");
        }
        Exam exam = examService.findById(examId).get();
        System.out.println("This is the number of students in this exam");
        System.out.println(exam.getCourse().getStudents().size());
        System.out.println("This is the list of students in this exam");
        exam.getCourse().getStudents().forEach(System.out::println);
        repository.commitTransaction();
    }
    @Override
    public void addExamToCourseByTeacherId(Long teacherId, Long courseId, Long examId) {
        /*repository.beginTransaction();*/
        if (repository.findById(teacherId).isEmpty()) {
            throw new RuntimeException("Teacher not found");
        }
        if (courseService.findById(courseId).isEmpty()) {
            throw new RuntimeException("Course not found");
        }
        if (examService.findById(examId).isEmpty()) {
            throw new RuntimeException("Exam not found");
        }
        if (courseService.findById(courseId).get().getTeacher().getId().equals(teacherId)) {
            Course course = courseService.findById(courseId).get();
            course.getExams().add(examService.findById(examId).get());
            courseService.save(course);

            Exam exam = examService.findById(examId).get();
            exam.setCourse(course);
            examService.save(exam);
            /*repository.commitTransaction();*/
        }
        else {
            throw new RuntimeException("You cannot add this exam because you didn't create it.");
        }
    }

    public void calculateScoreForStudentForEveryQuestion(Long studentId, Long questionId,
                                         Long answerToDQId, Long answerToMCQId ,
                                         Double givenScore) {
        if (studentService.findById(studentId).isEmpty()) {
            throw new RuntimeException("Student not found");
        }
        if (questionService.findById(questionId).isEmpty()) {
            throw new RuntimeException("Question not found");
        }
        if (answerToDQService.findById(answerToDQId).isEmpty()) {
            throw new RuntimeException("Answer to DQ not found");
        }
        if (answerToMCQService.findById(answerToMCQId).isEmpty()) {
            throw new RuntimeException("Answer to MCQ not found");
        }
        Question question = questionService.findById(questionId).get();
        if (question instanceof DescriptiveQuestion) {
            AnswerToDQ answerToDQ = answerToDQService.findById(answerToDQId).get();
            answerToDQ.setGivenScore(givenScore);
            answerToDQService.save(answerToDQ);
        } else if (question instanceof MultipleChoiceQuestion) {
            AnswerToMCQ answerToMCQ = answerToMCQService.findById(answerToMCQId).get();
            answerToMCQ.setGivenScore(givenScore);
            answerToMCQService.save(answerToMCQ);
        }
    }

    public void calculateFinalScoreForStudentInExam(Long studentId,
                                                    Long questionId, Long examId,
                                                    Long answerToDQId, Long answerToMCQId ,
                                                    Double givenScore) {
        repository.beginTransaction();
        Double finalScore = 0.0;
        if (examService.findById(examId).isEmpty()) {
            throw new RuntimeException("Exam not found");
        }
        if (studentService.findById(studentId).isEmpty()) {
            throw new RuntimeException("Student not found");
        }
        Exam exam = examService.findById(examId).get();
        exam.getQuestions().stream().forEach(question ->
                calculateScoreForStudentForEveryQuestion(studentId, questionId, answerToDQId, answerToMCQId,
                        givenScore));
    }
}
