package ir.maktabhw19.config;

import ir.maktabhw19.domains.Manager;
import ir.maktabhw19.domains.Teacher;
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
            studentService = new StudentServiceImpl(getStudentRepository());
        }
        return studentService;
    }

    private TeacherService teacherService;

    public TeacherService getTeacherService() {
        if(Objects.isNull(teacherService)){
            teacherService = new TeacherServiceImpl(getTeacherRepository());
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
}
