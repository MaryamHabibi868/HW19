package ir.maktabhw19.service;

import ir.maktabhw19.domains.Student;
import ir.maktabhw19.domains.Teacher;
import ir.maktabhw19.repository.TeacherRepository;
import ir.maktabhw19.service.base.BaseServiceImpl;

public class TeacherServiceImpl
        extends BaseServiceImpl<Teacher, Long, TeacherRepository>
        implements TeacherService{

    public TeacherServiceImpl(TeacherRepository repository) {
        super(repository);
    }

    public void registerTeacher(String firstName, String lastName,
                                String userName, String password) {
        repository.beginTransaction();
        Teacher teacher = new Teacher();
        teacher.setFirstName(firstName);
        teacher.setLastName(lastName);
        teacher.setUserName(userName);
        teacher.setPassword(password);
        repository.save(teacher);
        repository.commitTransaction();
        System.out.println("Teacher registered successfully");
    }
}
