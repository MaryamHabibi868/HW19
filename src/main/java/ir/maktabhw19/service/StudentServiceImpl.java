package ir.maktabhw19.service;


import ir.maktabhw19.domains.Manager;
import ir.maktabhw19.domains.Student;
import ir.maktabhw19.repository.StudentRepository;
import ir.maktabhw19.service.base.BaseServiceImpl;

public class StudentServiceImpl
        extends BaseServiceImpl<Student, Long, StudentRepository>
        implements StudentService{

    public StudentServiceImpl(StudentRepository repository) {
        super(repository);
    }

    public void registerStudent(String firstName, String lastName,
                                String userName, String password) {
        repository.beginTransaction();
        Student student = new Student();
        student.setFirstName(firstName);
        student.setLastName(lastName);
        student.setUserName(userName);
        student.setPassword(password);
        repository.save(student);
        repository.commitTransaction();
        System.out.println("Student registered successfully");
    }
}
