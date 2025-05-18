package ir.maktabhw19.service;


import ir.maktabhw19.domains.Student;
import ir.maktabhw19.repository.StudentRepository;
import ir.maktabhw19.service.base.BaseServiceImpl;

public class StudentServiceImpl
        extends BaseServiceImpl<Student, Long, StudentRepository>
        implements StudentService{

    public StudentServiceImpl(StudentRepository repository) {
        super(repository);
    }
}
