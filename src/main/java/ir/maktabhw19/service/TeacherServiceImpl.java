package ir.maktabhw19.service;

import ir.maktabhw19.domains.RegistrationConfirmation;
import ir.maktabhw19.domains.Student;
import ir.maktabhw19.domains.Teacher;
import ir.maktabhw19.repository.TeacherRepository;
import ir.maktabhw19.service.base.BaseServiceImpl;

import java.util.Optional;

public class TeacherServiceImpl
        extends BaseServiceImpl<Teacher, Long, TeacherRepository>
        implements TeacherService{

    public TeacherServiceImpl(TeacherRepository repository) {
        super(repository);
    }

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
        }
        else {
            System.out.println("userName not found");
        }
    }
}
