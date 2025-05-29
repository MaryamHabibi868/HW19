package ir.maktabhw19.service;


import ir.maktabhw19.domains.*;
import ir.maktabhw19.repository.StudentRepository;
import ir.maktabhw19.service.base.BaseServiceImpl;

import java.util.Optional;

public class StudentServiceImpl
        extends BaseServiceImpl<Student, Long, StudentRepository>
        implements StudentService{

    public StudentServiceImpl(StudentRepository repository,
                              ExamService examService,
                              CourseService courseService) {
        super(repository);
        this.examService = examService;
        this.courseService = courseService;
    }

    private final ExamService examService;
    private final CourseService courseService;

    @Override
    public Optional<Student> findStudentByUserName(String userName) {
        if (repository.findByUserName(userName).isEmpty()) {
            throw new RuntimeException("Student not found");
        }
        return repository.findByUserName(userName);
    }

    @Override
    public void registerStudent(String firstName, String lastName,
                                String userName, String password) {
        repository.beginTransaction();
        Student student = new Student();
        student.setFirstName(firstName);
        student.setLastName(lastName);
        student.setUserName(userName);
        student.setPassword(password);
        student.setRegistrationConfirmation(RegistrationConfirmation.PENDING);
        repository.save(student);
        repository.commitTransaction();
        System.out.println("Your registration is pending. " +
                "Manager should approve your registration ");
    }

    @Override
    public void loginStudent(String username, String password) {
        repository.beginTransaction();
        Student student = new Student();
        Optional<Student> foundStudent = repository.findByUserName(username);
        if (foundStudent.isPresent()) {
            if (foundStudent.get().getPassword().equals(password)) {
                System.out.println("Student logged in successfully");
            } else {
                System.out.println("Wrong password");
            }
        }
        else {
            System.out.println("userName not found");
        }
    }

    @Override
    public void participateInExamByStudent(Long studentId, Long examId) {
        repository.beginTransaction();
        if (repository.findById(studentId).isEmpty()) {
            throw new RuntimeException("Student not found");
        }
        if (examService.findById(examId).isEmpty()) {
            throw new RuntimeException("Exam not found");
        }
        Exam exam = examService.findById(examId).get();
        examService.save(exam);
        repository.save(repository.findById(studentId).get());
        repository.commitTransaction();
    }

    @Override
    public void printAllExamsForStudentByCourseId(Long studentId, Long courseId) {
        repository.beginTransaction();
        if (repository.findById(studentId).isEmpty()) {
            throw new RuntimeException("Student not found");
        }
        if (courseService.findById(courseId).isEmpty()) {
            throw new RuntimeException("Course not found");
        }
        Course course = courseService.findById(courseId).get();
        System.out.println("This is the list of all exams in this course");
        course.getExams().forEach(System.out::println);
        repository.commitTransaction();
    }
}
