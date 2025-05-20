package ir.maktabhw19.service;

import ir.maktabhw19.domains.Manager;
import ir.maktabhw19.domains.RegistrationConfirmation;
import ir.maktabhw19.domains.Student;
import ir.maktabhw19.domains.Teacher;
import ir.maktabhw19.repository.ManagerRepository;
import ir.maktabhw19.repository.TeacherRepository;
import ir.maktabhw19.service.base.BaseServiceImpl;

import java.util.Optional;

public class ManagerServiceImpl
        extends BaseServiceImpl<Manager, Long, ManagerRepository>
        implements ManagerService {

    public ManagerServiceImpl(ManagerRepository repository,
                              TeacherService teacherService,
                              StudentService studentService) {
        super(repository);
        this.teacherService = teacherService;
        this.studentService = studentService;
    }


    private final TeacherService teacherService;
    private final StudentService studentService;


    public void registerManager(String firstName, String lastName,
                                String userName, String password) {
        repository.beginTransaction();
        Manager manager = new Manager();
        manager.setFirstName(firstName);
        manager.setLastName(lastName);
        manager.setUserName(userName);
        manager.setPassword(password);
        repository.save(manager);
        repository.commitTransaction();
        System.out.println("Manager registered successfully");
    }

    public void loginManager(String username, String password) {
        repository.beginTransaction();
        Manager manager = new Manager();
        Optional<Manager> foundManager = repository.findByUsername(username);
        if (foundManager.isPresent()) {
            if (foundManager.get().getPassword().equals(password)) {
                System.out.println("Manager logged in successfully");
            } else {
                System.out.println("Wrong password");
            }
        }
        else {
            System.out.println("userName not found");
        }
    }

    public void  proofRegisteredTeacher(String userName) {
        repository.beginTransaction();
        Optional<Teacher> foundTeacher = teacherService.findTeacherByUserName(userName);
        if (foundTeacher.isPresent()) {
            Teacher teacher = foundTeacher.get();
            if (teacher.getRegistrationConfirmation() == RegistrationConfirmation.PENDING) {
                teacher.setRegistrationConfirmation(RegistrationConfirmation.APPROVED);
                System.out.println("Teacher registration APPROVED by manager successfully");
            } else {
                System.out.println("Teacher registration has already been approved");
            }
        }
    }

    public void  proofRegisteredStudent(String userName) {
        repository.beginTransaction();
        Optional<Student> foundStudent = studentService.findStudentByUserName(userName);
        if (foundStudent.isPresent()) {
            Student student = foundStudent.get();
            if (student.getRegistrationConfirmation() == RegistrationConfirmation.PENDING) {
                student.setRegistrationConfirmation(RegistrationConfirmation.APPROVED);
                System.out.println("Student registration APPROVED by manager successfully");
            } else {
                System.out.println("Student registration has already been approved");
            }

        }
    }
}
