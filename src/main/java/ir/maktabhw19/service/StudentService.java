package ir.maktabhw19.service;


import ir.maktabhw19.domains.Student;
import ir.maktabhw19.service.base.BaseService;

import java.util.Optional;

public interface StudentService
        extends BaseService<Student, Long> {

    Optional<Student> findStudentByUserName(String userName);
}
