package ir.maktabhw19.service;


import ir.maktabhw19.domains.Manager;
import ir.maktabhw19.domains.RegistrationConfirmation;
import ir.maktabhw19.domains.Student;
import ir.maktabhw19.repository.StudentRepository;
import ir.maktabhw19.service.base.BaseServiceImpl;

import java.util.Optional;

public class StudentServiceImpl
        extends BaseServiceImpl<Student, Long, StudentRepository>
        implements StudentService{

    public StudentServiceImpl(StudentRepository repository) {
        super(repository);
    }


    @Override
    public Optional<Student> findStudentByUserName(String userName) {
        if (repository.findByUsername(userName).isEmpty()) {
            throw new RuntimeException("Student not found");
        }
        return repository.findByUsername(userName);
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
        Optional<Student> foundStudent = repository.findByUsername(username);
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
}
