package ir.maktabhw19.config;

import ir.maktabhw19.domains.AnswerToDQ;
import ir.maktabhw19.repository.*;
import ir.maktabhw19.service.*;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.util.Objects;

public class ApplicationContext {
    private static ApplicationContext applicationContext;

    private ApplicationContext() {
    }

    public static ApplicationContext getInstance(){
        if(Objects.isNull(applicationContext)){
            applicationContext = new ApplicationContext();
        }
        return applicationContext;
    }

    private EntityManagerFactory entityManagerFactory;

    public EntityManagerFactory getEntityManagerFactory() {
        if(Objects.isNull(entityManagerFactory)){
            entityManagerFactory = Persistence.createEntityManagerFactory("default");
        }
        return entityManagerFactory;
    }

    private EntityManager entityManager;

    public EntityManager getEntityManager() {
        if(Objects.isNull(entityManager)){
            entityManager = getEntityManagerFactory().createEntityManager();
        }
        return entityManager;
    }

    private StudentRepository studentRepository;

    public StudentRepository getStudentRepository() {
        if(Objects.isNull(studentRepository)){
            studentRepository = new StudentRepositoryImpl(getEntityManager());
        }
        return studentRepository;
    }

    private ManagerRepository managerRepository;

    public ManagerRepository getManagerRepository() {
        if(Objects.isNull(managerRepository)){
            managerRepository = new ManagerRepositoryImpl(getEntityManager());
        }
        return managerRepository;
    }

    private TeacherRepository teacherRepository;

    public TeacherRepository getTeacherRepository() {
        if(Objects.isNull(teacherRepository)){
            teacherRepository = new TeacherRepositoryImpl(getEntityManager());
        }
        return teacherRepository;
    }

    private CourseRepository courseRepository;

    public CourseRepository getCourseRepository() {
        if(Objects.isNull(courseRepository)){
            courseRepository = new CourseRepositoryImpl(getEntityManager());
        }
        return courseRepository;
    }

    private ExamRepository examRepository;

    public ExamRepository getExamRepository() {
        if(Objects.isNull(examRepository)){
            examRepository = new ExamRepositoryImpl(getEntityManager());
        }
        return examRepository;
    }

    private QuestionRepository questionRepository;

    public QuestionRepository getQuestionRepository() {
        if(Objects.isNull(questionRepository)){
            questionRepository = new QuestionRepositoryImpl(getEntityManager());
        }
        return questionRepository;
    }

    private DescriptiveQuestionRepository descriptiveQuestionRepository;

    public DescriptiveQuestionRepository getDescriptiveQuestionsRepository() {
        if(Objects.isNull(descriptiveQuestionRepository)){
            descriptiveQuestionRepository =
                    new DescriptiveQuestionRepositoryImpl(getEntityManager());
        }
        return descriptiveQuestionRepository;
    }

    private MultipleChoiceQuestionRepository multipleChoiceQuestionRepository;

    public MultipleChoiceQuestionRepository getMultipleChoiceQuestionRepository() {
        if(Objects.isNull(multipleChoiceQuestionRepository)){
            multipleChoiceQuestionRepository =
                    new MultipleChoiceQuestionRepositoryImpl(getEntityManager());
        }
        return multipleChoiceQuestionRepository;
    }

    private AnswerRepository answerRepository;

    public AnswerRepository getAnswerRepository() {
        if(Objects.isNull(answerRepository)){
            answerRepository =
                    new AnswerRepositoryImpl(getEntityManager());
        }
        return answerRepository;
    }

    private AnswerToDQRepository answerToDQRepository;

    public AnswerToDQRepository getAnswerToDQRepository() {
        if(Objects.isNull(answerToDQRepository)){
            answerToDQRepository =
                    new AnswerToDQRepositoryImpl(getEntityManager());
        }
        return answerToDQRepository;
    }

    private AnswerToMCQRepository answerToMCQRepository;

    public AnswerToMCQRepository getAnswerToMCQRepository() {
        if(Objects.isNull(answerToMCQRepository)){
            answerToMCQRepository =
                    new AnswerToMCQRepositoryImpl(getEntityManager());
        }
        return answerToMCQRepository;
    }

    private ManagerService managerService;

    public ManagerService getManagerService() {
        if(Objects.isNull(managerService)){
            managerService = new ManagerServiceImpl(getManagerRepository(),
                    getTeacherService(),
                    getStudentService(),
                    getCourseService());
        }
        return managerService;
    }

    private StudentService studentService;

    public StudentService getStudentService() {
        if(Objects.isNull(studentService)){
            studentService = new StudentServiceImpl(getStudentRepository(),
            getExamService());
        }
        return studentService;
    }

    private TeacherService teacherService;

    public TeacherService getTeacherService() {
        if(Objects.isNull(teacherService)){
            teacherService = new TeacherServiceImpl(getTeacherRepository(),
                    getExamService(),
                    getDescriptiveQuestionsService(),
                    getMultipleChoiceQuestionService(),
                    getQuestionService());
        }
        return teacherService;
    }

    private CourseService courseService;

    public CourseService getCourseService() {
        if(Objects.isNull(courseService)){
            courseService = new CourseServiceImpl(getCourseRepository());
        }
        return courseService;
    }

    private ExamService examService;

    public ExamService getExamService() {
        if(Objects.isNull(examService)){
            examService = new ExamServiceImpl(getExamRepository());
        }
        return examService;
    }

    private QuestionService questionService;

    public QuestionService getQuestionService() {
        if(Objects.isNull(questionService)){
            questionService = new QuestionServiceImpl(getQuestionRepository());
        }
        return questionService;
    }

    private DescriptiveQuestionService descriptiveQuestionService;

    public DescriptiveQuestionService getDescriptiveQuestionsService() {
        if(Objects.isNull(descriptiveQuestionService)){
            descriptiveQuestionService =
                    new DescriptiveQuestionServiceImpl(getDescriptiveQuestionsRepository());
        }
        return descriptiveQuestionService;
    }

    private MultipleChoiceQuestionService multipleChoiceQuestionService;

    public MultipleChoiceQuestionService getMultipleChoiceQuestionService() {
        if(Objects.isNull(multipleChoiceQuestionService)){
            multipleChoiceQuestionService =
                    new MultipleChoiceQuestionServiceImpl(getMultipleChoiceQuestionRepository());
        }
        return multipleChoiceQuestionService;
    }

    private AnswerService answerService;

    public AnswerService getAnswerService() {
        if(Objects.isNull(answerService)){
            answerService =
                    new AnswerServiceImpl(getAnswerRepository());
        }
        return answerService;
    }

    private AnswerToDQService answerToDQService;

    public AnswerToDQService getAnswerToDQService() {
        if(Objects.isNull(answerToDQService)){
            answerToDQService =
                    new AnswerToDQServiceImpl(getAnswerToDQRepository(),
                            getStudentService(),
                            getDescriptiveQuestionsService());
        }
        return answerToDQService;
    }

    private AnswerToMCQService answerToMCQService;

    public AnswerToMCQService getAnswerToMCQService() {
        if(Objects.isNull(answerToMCQService)){
            answerToMCQService =
                    new AnswerToMCQServiceImpl(getAnswerToMCQRepository());
        }
        return answerToMCQService;
    }
}
