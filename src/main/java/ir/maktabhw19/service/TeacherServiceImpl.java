package ir.maktabhw19.service;

import ir.maktabhw19.domains.Teacher;
import ir.maktabhw19.repository.TeacherRepository;
import ir.maktabhw19.service.base.BaseServiceImpl;

public class TeacherServiceImpl
        extends BaseServiceImpl<Teacher, Long, TeacherRepository>
        implements TeacherService{

    public TeacherServiceImpl(TeacherRepository repository) {
        super(repository);
    }
}
