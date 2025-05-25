package ir.maktabhw19;


import ir.maktabhw19.config.ApplicationContext;
import ir.maktabhw19.domains.Manager;
import ir.maktabhw19.service.ManagerService;
import ir.maktabhw19.service.ManagerServiceImpl;
import ir.maktabhw19.service.TeacherService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        EntityManager entityManager = ApplicationContext.getInstance().getEntityManager();

        ApplicationContext context = ApplicationContext.getInstance();
        ManagerService managerService = context.getManagerService();
        TeacherService teacherService = context.getTeacherService();

        System.out.println("Welcome To A project for managing the scheduling, creation, and execution of exams");
        System.out.println("""
                 Please Indicate your role.
                 1) Manager
                 2) Teacher
                 3) Student
                """);
        Integer choice = scanner.nextInt();
        switch (choice) {
            case 1:
                System.out.println("""
                        Do you want to 
                        1)LOGIN 
                        or 
                        2)REGISTER?""");
                Integer managerChoice = scanner.nextInt();
                switch (managerChoice) {
                    case 1:
                        System.out.println("Please enter your USERNAME");
                        String userName = scanner.next();
                        System.out.println("Please enter your PASSWORD");
                        String password = scanner.next();
                        managerService.loginManager(userName, password);
                        break;

                    case 2:
                        System.out.println("Please enter your First NAME");
                        String firstName = scanner.next();
                        System.out.println("Please enter your Last NAME");
                        String lastName = scanner.next();
                        System.out.println("Please enter your USERNAME");
                        String userName1 = scanner.next();
                        System.out.println("Please enter your PASSWORD");
                        String password1 = scanner.next();
                        managerService.registerManager(firstName, lastName, userName1, password1);


                        System.out.println("""
                                Enter what you would like to do
                                1) Prove registration of teachers?
                                2) Prove registration of students?
                                3) Add COURSE?
                                4) Add teacher to course?
                                5) Add student to course?""");
                        Integer managerChoice2 = scanner.nextInt();
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
                        }
                }
                break;

            case 2:
                System.out.println("""
                        Do you want to 
                        1)LOGIN 
                        or 
                        2)REGISTER?""");
                Integer teacherChoice = scanner.nextInt();
                switch (teacherChoice) {
                    case 1:
                        System.out.println("Please enter your USERNAME");
                        String userName = scanner.next();
                        System.out.println("Please enter your PASSWORD");
                        String password = scanner.next();
                        teacherService.loginTeacher(userName, password);
                        break;

                    case 2:
                        System.out.println("Please enter your First NAME");
                        String firstName = scanner.next();
                        System.out.println("Please enter your Last NAME");
                        String lastName = scanner.next();
                        System.out.println("Please enter your USERNAME");
                        String userName1 = scanner.next();
                        System.out.println("Please enter your PASSWORD");
                        String password1 = scanner.next();
                        teacherService.registerTeacher(firstName, lastName, userName1, password1);


                        System.out.println("""
                                Enter what you would like to do
                                1) Prove registration of teachers?
                                2) Prove registration of students?
                                3) Add COURSE?
                                4) Add teacher to course?
                                5) Add student to course?""");
                        Integer managerChoice2 = scanner.nextInt();
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
                        }
                }
                break;


        }


    }
}