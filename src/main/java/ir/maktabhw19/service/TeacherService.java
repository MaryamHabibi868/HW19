package ir.maktabhw19.service;


import ir.maktabhw19.domains.Teacher;
import ir.maktabhw19.service.base.BaseService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface TeacherService
        extends BaseService<Teacher, Long> {

     Optional<Teacher> findTeacherByUserName(String userName);

     void loginTeacher(String username, String password);

     void registerTeacher(String firstName, String lastName,
                          String userName, String password);

     void addExamByTeacher(Long teacherId, LocalDateTime startDate,
                           LocalDateTime endDate);

     void addQuestionToExamByTeacherFromBankQuestion(
             Long teacherId, Long examId, Long questionId, Double score);

     void addDescriptiveQuestionToExamByTeacher(
             Long teacherId, Long examId,
             String title, String statement,
             Double score);

     void addMCQuestionToExamByTeacher(
             Long teacherId, Long examId,
             String title, String statement,
             List<String> options, Integer indexOfCorrectAnswer,
             Double score);

     void runExam(Long examId);

     void printAllCoursesByTeacherId(Long teacherId);

     void printAllExamsByCourseId(Long courseId);

     void printAllExamsInCourseByTeacherId(Long teacherId,
                                           Long courseId);

     public void removeExamFromCourseByTeacherId(Long teacherId,
                                                 Long examId);

     void printAllQuestionsByTeacherId(Long teacherId, Long courseId);
}
