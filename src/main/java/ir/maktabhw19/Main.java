package ir.maktabhw19;


import ir.maktabhw19.config.ApplicationContext;
import ir.maktabhw19.domains.Manager;
import ir.maktabhw19.domains.Student;
import ir.maktabhw19.domains.Teacher;
import ir.maktabhw19.service.ManagerService;
import ir.maktabhw19.service.StudentService;
import ir.maktabhw19.service.TeacherService;
import jakarta.persistence.EntityManager;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        EntityManager entityManager = ApplicationContext.getInstance().getEntityManager();

        ApplicationContext context = ApplicationContext.getInstance();
        ManagerService managerService = context.getManagerService();
        TeacherService teacherService = context.getTeacherService();
        StudentService studentService = context.getStudentService();

        System.out.println("Welcome To A project for managing the scheduling, creation, and execution of exams");
        System.out.println("""
                 Please Indicate your role.
                 1) Manager
                 2) Teacher
                 3) Student
                """);
        int choice = scanner.nextInt();
        switch (choice) {
            case 1:
                System.out.println("""
                        Do you want to 
                        1)REGISTER 
                        or 
                        2)LOGIN""");
                int managerChoice = scanner.nextInt();
                switch (managerChoice) {
                    case 1:
                        System.out.println("Please enter your First NAME");
                        String firstName = scanner.next();
                        System.out.println("Please enter your Last NAME");
                        String lastName = scanner.next();
                        System.out.println("Please enter your USERNAME");
                        String userName1 = scanner.next();
                        System.out.println("Please enter your PASSWORD");
                        String password1 = scanner.next();
                        managerService.registerManager(firstName, lastName, userName1, password1);
                        System.out.println("Please enter again for using Application");
                        break;

                    case 2:
                        System.out.println("Please enter your USERNAME");
                        String userName = scanner.next();
                        System.out.println("Please enter your PASSWORD");
                        String password = scanner.next();
                        managerService.loginManager(userName, password);
                        Manager currentManager = managerService.findManagerByUserName(userName).get();
                        Long currentManagerId = currentManager.getId();

                        boolean managerLoggedIn = true;
                        while (managerLoggedIn) {
                            System.out.println("""
                                    Enter what you would like to do
                                    1) Prove registration of teachers?
                                    2) Prove registration of students?
                                    3) Add COURSE?
                                    4) Add teacher to course?
                                    5) Add student to course?
                                    6) Print All Courses
                                    7) Print All Teachers
                                    8) Print All Students
                                    9) Changed Student To Teacher
                                    10) Changed Teacher To Student
                                    11) Remove Course
                                    12) Remove Teacher From Course
                                    13) Remove Student From Course
                                    14) Print All USERS in the Course
                                    15) Exit""");
                            int managerChoice2 = scanner.nextInt();
                            scanner.nextLine();
                            switch (managerChoice2) {
                                case 1:
                                    System.out.println("Please enter the username of teacher");
                                    String userName2 = scanner.next();
                                    managerService.proofRegisteredTeacher(userName2);
                                    break;

                                case 2:
                                    System.out.println("Please enter the username of student");
                                    String userName3 = scanner.next();
                                    managerService.proofRegisteredStudent(userName3);
                                    break;

                                case 3:
                                    System.out.println("Please enter the title of course");
                                    String title = scanner.next();
                                    System.out.println("Please enter the starting date");
                                    LocalDate startDate = LocalDate.parse(scanner.next());
                                    System.out.println("Please enter the ending date");
                                    LocalDate endDate = LocalDate.parse(scanner.next());
                                    managerService.addCourse(title, startDate, endDate);
                                    break;

                                case 4:
                                    System.out.println("Please enter the ID of course");
                                    Long courseId = scanner.nextLong();
                                    System.out.println("Please enter the ID of teacher");
                                    Long teacherId = scanner.nextLong();
                                    managerService.addTeacherToCourse(courseId, teacherId);
                                    break;

                                case 5:
                                    System.out.println("Please enter the ID of student");
                                    Long studentId = scanner.nextLong();
                                    System.out.println("Please enter the ID of course");
                                    Long courseId1 = scanner.nextLong();
                                    managerService.addStudentToCourse(studentId, courseId1);
                                    break;

                                case 6:
                                    System.out.println(managerService.printAllCourses());
                                    break;

                                case 7:
                                    System.out.println(managerService.printAllTeachers());
                                    break;

                                case 8:
                                    System.out.println(managerService.printAllStudents());
                                    break;

                                case 9:
                                    System.out.println("Please enter the ID of student");
                                    Long studentId1 = scanner.nextLong();
                                    managerService.changeStudentToTeacher(studentId1);
                                    break;

                                case 10:
                                    System.out.println("Please enter the ID of teacher");
                                    Long teacherId1 = scanner.nextLong();
                                    managerService.changeTeacherToStudent(teacherId1);
                                    break;

                                case 11:
                                    System.out.println("Please enter the ID of course");
                                    Long courseId2 = scanner.nextLong();
                                    managerService.removeCourse(courseId2);
                                    break;

                                case 12:
                                    System.out.println("Please enter the ID of course");
                                    Long courseId3 = scanner.nextLong();
                                    System.out.println("Please enter the ID of teacher");
                                    Long teacherId2 = scanner.nextLong();
                                    managerService.removeTeacherFromCourse(courseId3, teacherId2);
                                    break;

                                case 13:
                                    System.out.println("Please enter the ID of course");
                                    Long courseId4 = scanner.nextLong();
                                    System.out.println("Please enter the ID of student");
                                    Long studentId3 = scanner.nextLong();
                                    managerService.removeStudentFromCourse(studentId3, courseId4);
                                    break;

                                case 14:
                                    System.out.println("""
                                            This is the List of Courses.
                                            Please enter the ID of course 
                                            you would like to see All the Users""");
                                    managerService.printAllCourses();
                                    Long courseId5 = scanner.nextLong();
                                    managerService.printUsersByCourseID(courseId5);
                                    break;

                                case 15:
                                    managerLoggedIn = false;
                            }
                        }
                }

            case 2:
                System.out.println("""
                        Do you want to 
                        1)REGISTER 
                        or 
                        2)LOGIN""");
                int teacherChoice = scanner.nextInt();
                scanner.nextLine();
                switch (teacherChoice) {
                    case 1:
                        System.out.println("Please enter your First NAME");
                        String firstName = scanner.next();
                        System.out.println("Please enter your Last NAME");
                        String lastName = scanner.next();
                        System.out.println("Please enter your USERNAME");
                        String userName1 = scanner.next();
                        System.out.println("Please enter your PASSWORD");
                        String password1 = scanner.next();
                        teacherService.registerTeacher(firstName, lastName, userName1, password1);
                        System.out.println("Please enter again for using Application");
                        break;

                    case 2:
                        System.out.println("Please enter your USERNAME");
                        String userName = scanner.next();
                        System.out.println("Please enter your PASSWORD");
                        String password = scanner.next();
                        teacherService.loginTeacher(userName, password);
                        Teacher currentTeacher = teacherService.findTeacherByUserName(userName).get();
                        Long currentTeacherId = currentTeacher.getId();

                        boolean teacherLoggedIn = true;
                        while (teacherLoggedIn) {
                            System.out.println("""
                                    Enter what you would like to do
                                    1) Print All Courses
                                    2) Print All the Exams in the Course
                                    3) Print All the Exams you had run
                                    4) Remove the Exam
                                    5) Add Exam?
                                    6) Add Question To Exam From BankQuestion?
                                    7) Add Descriptive Question To Exam?
                                    8) Add Multiple Choice Question To Exam?
                                    9) Run Exam?
                                    10) Print All the Questions in the Course
                                    11) Print All the Students participate in the Exam
                                    12) Exit""");
                            int teacherChoice2 = scanner.nextInt();
                            scanner.nextLine();
                            switch (teacherChoice2) {

                                case 1:
                                    teacherService.printAllCoursesByTeacherId(currentTeacherId);
                                    break;

                                case 2:
                                    System.out.println("""
                                            According to the List of Courses
                                            Please enter the ID of course 
                                            you would like to see All the Exams""");
                                    Long courseId11 = scanner.nextLong();
                                    teacherService.printAllExamsByCourseId(courseId11);
                                    break;

                                case 3:
                                    System.out.println("""
                                            Please Enter course ID again.
                                            This is the List of Exams
                                            You had create
                                            According to the course
                                            you had selected""");
                                    Long courseId12 = scanner.nextLong();
                                    teacherService.printAllExamsInCourseByTeacherId(currentTeacherId, courseId12);
                                    break;

                                case 4:
                                    System.out.println("Please enter the ID of Exam");
                                    Long id15 = scanner.nextLong();
                                    teacherService.removeExamFromCourseByTeacherId(currentTeacherId, id15);
                                    break;

                                case 5:
                                    System.out.println("Please enter the starting date");
                                    LocalDateTime startDate = LocalDateTime.parse(scanner.next());
                                    System.out.println("Please enter the ending date");
                                    LocalDateTime endDate = LocalDateTime.parse(scanner.next());
                                    teacherService.addExamByTeacher(currentTeacherId, startDate, endDate);
                                    break;

                                case 6:
                                    System.out.println("Please enter the ID of exam");
                                    Long id2 = scanner.nextLong();
                                    System.out.println("Please enter the ID of question");
                                    Long id3 = scanner.nextLong();
                                    System.out.println("Please enter the default score");
                                    Double score1 = scanner.nextDouble();
                                    teacherService.addQuestionToExamByTeacherFromBankQuestion(currentTeacherId, id2, id3, score1);
                                    break;

                                case 7:
                                    System.out.println("Please enter the ID of exam");
                                    Long id5 = scanner.nextLong();
                                    System.out.println("Please enter the title of question");
                                    String title1 = scanner.next();
                                    System.out.println("Please enter the statement of question");
                                    String statement1 = scanner.next();
                                    System.out.println("Please enter the default score");
                                    Double score2 = scanner.nextDouble();
                                    teacherService.addDescriptiveQuestionToExamByTeacher(currentTeacherId, id5, title1, statement1, score2);
                                    break;

                                case 8:
                                    System.out.println("Please enter the ID of exam");
                                    Long id7 = scanner.nextLong();
                                    System.out.println("Please enter the title of question");
                                    String title2 = scanner.next();
                                    System.out.println("Please enter the statement of question");
                                    String statement2 = scanner.next();
                                    System.out.println("Please enter how many options you would like to add?");
                                    int options = scanner.nextInt();
                                    List<String> optionsList = new ArrayList<>();
                                    for (int i = 1; i < options; i++) {
                                        System.out.println("Please enter the option");
                                        String option = scanner.next();
                                        optionsList.add(option);
                                    }
                                    System.out.println("Please enter the index of correct answer");
                                    Integer indexOfCorrectAnswer = scanner.nextInt();
                                    System.out.println("Please enter the default score");
                                    Double score3 = scanner.nextDouble();
                                    teacherService.addMCQuestionToExamByTeacher(currentTeacherId, id7, title2, statement2, optionsList, indexOfCorrectAnswer, score3);
                                    break;

                                case 9:
                                    System.out.println("Please enter the ID of exam");
                                    Long id8 = scanner.nextLong();
                                    teacherService.runExam(id8);
                                    break;

                                case 10:
                                    System.out.println("Please enter your ID");
                                    Long id16 = scanner.nextLong();
                                    System.out.println("Please enter the ID of Course");
                                    Long id17 = scanner.nextLong();
                                    teacherService.printAllQuestionsByTeacherId(id16, id17);
                                    break;

                                case 11:
                                    System.out.println("Please enter Exam ID");
                                    Long id18 = scanner.nextLong();
                                    teacherService.printAllStudentInExamByExamId(id18);

                                case 12:
                                    teacherLoggedIn = false;
                            }
                        }
                }

            case 3:
                System.out.println("""
                        Do you want to 
                        1) REGISTER 
                        or 
                        2)LOGIN""");
                int studentChoice = scanner.nextInt();
                switch (studentChoice) {
                    case 1:
                        System.out.println("Please enter your First NAME");
                        String firstName = scanner.next();
                        System.out.println("Please enter your Last NAME");
                        String lastName = scanner.next();
                        System.out.println("Please enter your USERNAME");
                        String userName1 = scanner.next();
                        System.out.println("Please enter your PASSWORD");
                        String password1 = scanner.next();
                        studentService.registerStudent(firstName, lastName, userName1, password1);
                        System.out.println("Please enter again for using Application");
                        break;

                    case 2:
                        System.out.println("Please enter your USERNAME");
                        String userName = scanner.next();
                        System.out.println("Please enter your PASSWORD");
                        String password = scanner.next();
                        studentService.loginStudent(userName, password);
                        Student currentStudent = studentService.findStudentByUserName(userName).get();
                        Long currentStudentId = currentStudent.getId();

                        boolean teacherLoggedIn = true;
                        while (teacherLoggedIn) {
                            System.out.println("""
                                    Enter what you would like to do
                                    1) Participate in the Exam?
                                    2) Print All the Exams in this Course""");

                            int studentChoice1 = scanner.nextInt();

                            switch (studentChoice1) {
                                case 1:
                                    System.out.println("Please enter the ID of exam");
                                    Long id2 = scanner.nextLong();
                                    studentService.participateInExamByStudent(currentStudentId, id2);
                                    break;

                                case 2:
                                    System.out.println("Please enter the ID of course");
                                    Long id4 = scanner.nextLong();
                                    studentService.printAllExamsForStudentByCourseId(currentStudentId, id4);
                                    break;
                            }
                        }
                }
        }
    }
}