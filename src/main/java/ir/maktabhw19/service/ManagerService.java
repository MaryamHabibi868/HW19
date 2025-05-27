package ir.maktabhw19.service;

import ir.maktabhw19.domains.Course;
import ir.maktabhw19.domains.Manager;
import ir.maktabhw19.domains.Student;
import ir.maktabhw19.domains.Teacher;
import ir.maktabhw19.service.base.BaseService;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

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

    List<Course> printAllCourses();

    List<Teacher> printAllTeachers();

    List<Student> printAllStudents();

    void changeStudentToTeacher(Long studentId);

    void changeTeacherToStudent(Long teacherId);
}
