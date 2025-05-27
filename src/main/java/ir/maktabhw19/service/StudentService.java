package ir.maktabhw19.service;


import ir.maktabhw19.domains.Student;
import ir.maktabhw19.service.base.BaseService;

import java.util.Optional;

public interface StudentService
        extends BaseService<Student, Long> {

    Optional<Student> findStudentByUserName(String userName);

    void loginStudent(String username, String password);

    void registerStudent(String firstName, String lastName,
                         String userName, String password);

    void participateInExamByStudent(Long studentId, Long examId);
}
