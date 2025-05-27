package ir.maktabhw19;


import ir.maktabhw19.config.ApplicationContext;
import ir.maktabhw19.domains.Manager;
import ir.maktabhw19.service.ManagerService;
import ir.maktabhw19.service.ManagerServiceImpl;
import ir.maktabhw19.service.StudentService;
import ir.maktabhw19.service.TeacherService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import lombok.Lombok;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
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
                                5) Add student to course?
                                6) Print All Courses
                                7) Print All Teachers
                                8) Print All Students""");
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
                                1) Add Exam?
                                2) Add Question To Exam From BankQuestion?
                                3) Add Descriptive Question To Exam?
                                4) Add Multiple Choice Question To Exam?
                                5) Run Exam?""");
                        Integer teacherChoice2 = scanner.nextInt();
                        switch (teacherChoice2) {
                            case 1:
                                System.out.println("Please enter your ID");
                                Long id = scanner.nextLong();
                                System.out.println("Please enter the starting date");
                                LocalDateTime startDate = LocalDateTime.parse(scanner.next());
                                System.out.println("Please enter the ending date");
                                LocalDateTime endDate = LocalDateTime.parse(scanner.next());
                                teacherService.addExamByTeacher(id, startDate, endDate);
                                break;

                            case 2:
                                System.out.println("Please enter your ID");
                                Long id1 = scanner.nextLong();
                                System.out.println("Please enter the ID of exam");
                                Long id2 = scanner.nextLong();
                                System.out.println("Please enter the ID of question");
                                Long id3 = scanner.nextLong();
                                System.out.println("Please enter the default score");
                                Double score1 = scanner.nextDouble();
                                teacherService.addQuestionToExamByTeacherFromBankQuestion(id1, id2, id3, score1);
                                break;

                            case 3:
                                System.out.println("Please enter the your ID");
                                Long id4 = scanner.nextLong();
                                System.out.println("Please enter the ID of exam");
                                Long id5 = scanner.nextLong();
                                System.out.println("Please enter the title of question");
                                String title1 = scanner.next();
                                System.out.println("Please enter the statement of question");
                                String statement1 = scanner.next();
                                System.out.println("Please enter the default score");
                                Double score2 = scanner.nextDouble();
                                teacherService.addDescriptiveQuestionToExamByTeacher(id4, id5, title1, statement1, score2);
                                break;

                            case 4:
                                System.out.println("Please enter your ID");
                                Long id6 = scanner.nextLong();
                                System.out.println("Please enter the ID of exam");
                                Long id7 = scanner.nextLong();
                                System.out.println("Please enter the title of question");
                                String title2 = scanner.next();
                                System.out.println("Please enter the statement of question");
                                String statement2 = scanner.next();
                                System.out.println("Please enter how many options you would like to add?");
                                Integer options = scanner.nextInt();
                                List<String> optionsList = new ArrayList<String>();
                                for (int i = 1; i < options; i++) {
                                    System.out.println("Please enter the option");
                                    String option = scanner.next();
                                    optionsList.add(option);
                                }
                                System.out.println("Please enter the index of correct answer");
                                Integer indexOfCorrectAnswer = scanner.nextInt();
                                System.out.println("Please enter the default score");
                                Double score3 = scanner.nextDouble();
                                teacherService.addMCQuestionToExamByTeacher(id6, id7, title2, statement2, optionsList, indexOfCorrectAnswer, score3);
                                break;

                            case 5:
                                System.out.println("Please enter the ID of exam");
                                Long id8 = scanner.nextLong();
                                teacherService.runExam(id8);
                                break;
                        }
                }
                break;

            case 3:
                System.out.println("""
                        Do you want to 
                        1)LOGIN 
                        or 
                        2)REGISTER?""");
                Integer studentChoice = scanner.nextInt();
                switch (studentChoice) {
                    case 1:
                        System.out.println("Please enter your USERNAME");
                        String userName = scanner.next();
                        System.out.println("Please enter your PASSWORD");
                        String password = scanner.next();
                        studentService.loginStudent(userName, password);
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
                        studentService.registerStudent(firstName, lastName, userName1, password1);

                        System.out.println("""
                                Enter what you would like to do
                                1) Participate in the Exam?""");

                        Integer studentChoice1 = scanner.nextInt();

                        switch (studentChoice1) {
                            case 1:
                                System.out.println("Please enter your ID");
                                Long id1 = scanner.nextLong();
                                System.out.println("Please enter the ID of exam");
                                Long id2 = scanner.nextLong();
                                studentService.participateInExamByStudent(id1, id2);
                                break;
                        }
                }
        }
    }
}