package ir.maktabhw19.service;

import ir.maktabhw19.domains.Manager;
import ir.maktabhw19.service.base.BaseService;

import java.time.LocalDate;
import java.time.LocalDateTime;

public interface ManagerService
        extends BaseService<Manager, Long> {

    void registerManager(String firstName, String lastName,
                         String userName, String password);

    void loginManager(String username, String password);

    void proofRegisteredTeacher(String userName);

    void proofRegisteredStudent(String userName);

    void addCourse(String title, LocalDate startDate, LocalDate endDate);

    void addTeacherToCourse(Long courseId, Long teacherId);

    void addStudentToCourse(Long studentId, Long courseId);
}
