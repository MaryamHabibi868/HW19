package ir.maktabhw19;


import ir.maktabhw19.config.ApplicationContext;
import ir.maktabhw19.domains.*;
import ir.maktabhw19.service.*;
import jakarta.persistence.EntityManager;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
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
        CourseService courseService = context.getCourseService();
        QuestionService questionService = context.getQuestionService();
        DescriptiveQuestionService descriptiveQuestionService = context.getDescriptiveQuestionsService();
        MultipleChoiceQuestionService multipleChoiceQuestionService = context.getMultipleChoiceQuestionService();
        ExamService examService = context.getExamService();
        AnswerToDQService answerToDQService = context.getAnswerToDQService();
        AnswerToMCQService answerToMCQService = context.getAnswerToMCQService();

        // Add Manager
        Manager manager = Manager.builder()
                .firstName("Ali")
                .lastName("Habibi")
                .userName("alihabibi")
                .password("123")
                .build();
        managerService.save(manager);


        //Add teacher
        Teacher teacher = Teacher.builder()
                .firstName("Maryam")
                .lastName("Sadidi")
                .userName("maryamsadidi")
                .password("12345")
                .registrationConfirmation(RegistrationConfirmation.PENDING)
                .build();
        Teacher teacher1 = Teacher.builder()
                .firstName("Mohamad")
                .lastName("Zaki")
                .userName("mohamadzaki")
                .password("987")
                .registrationConfirmation(RegistrationConfirmation.PENDING)
                .build();
        teacherService.save(teacher);
        teacherService.save(teacher1);


        // Add Student
        Student student = Student.builder()
                .firstName("Yasna")
                .lastName("Hoseini")
                .userName("yasnahoseini")
                .password("7654")
                .registrationConfirmation(RegistrationConfirmation.PENDING)
                .build();
        Student student1 = Student.builder()
                .firstName("Salma")
                .lastName("Ashrafi")
                .userName("salmaashrafi")
                .password("76543")
                .registrationConfirmation(RegistrationConfirmation.PENDING)
                .build();
        Student student2 = Student.builder()
                .firstName("Helma")
                .lastName("Shojaei")
                .userName("helmashojaei")
                .password("65443")
                .registrationConfirmation(RegistrationConfirmation.PENDING)
                .build();
        studentService.save(student);
        studentService.save(student1);
        studentService.save(student2);


        // Add Course
        Course course = Course.builder()
                .title("Math")
                .startDate(LocalDate.parse("2025-01-01"))
                .endDate(LocalDate.parse("2025-06-20"))
                .students(new HashSet<>())
                .exams(new HashSet<>())
                .build();
        Course course1 = Course.builder()
                .title("Physics")
                .startDate(LocalDate.parse("2025-02-01"))
                .endDate(LocalDate.parse("2025-07-20"))
                .students(new HashSet<>())
                .exams(new HashSet<>())
                .build();
        courseService.save(course);
        courseService.save(course1);


        // Add Descriptive Question
        DescriptiveQuestion question = DescriptiveQuestion.builder()
                .questionTitle("Add Number")
                .questionStatement("Add 2+5?")
                .build();
        DescriptiveQuestion question2 = DescriptiveQuestion.builder()
                .questionTitle("Speed")
                .questionStatement("Explain the speed of motorcycle in the street")
                .build();
        descriptiveQuestionService.save(question);
        descriptiveQuestionService.save(question2);


        // Add Multiple Choice Question
        MultipleChoiceQuestion question1 = MultipleChoiceQuestion.builder()
                .questionTitle("Average")
                .questionStatement("Find average between 10 and 20")
                .options(List.of("11", "20", "15"))
                .correctOptionIndex(2)
                .build();
        MultipleChoiceQuestion question3 = MultipleChoiceQuestion.builder()
                .questionTitle("Pressure")
                .questionStatement("Find the pressure in 20 meters in the sea")
                .options(List.of("6", "700", "65"))
                .correctOptionIndex(1)
                .build();

        multipleChoiceQuestionService.save(question1);
        multipleChoiceQuestionService.save(question3);


        // Add Exam
        Exam exam = Exam.builder()
                .startDate(LocalDateTime.of(2025, 5, 3, 14, 30))
                .endDate(LocalDateTime.of(2025, 5, 3, 16, 30))
                .build();
        Exam exam1 = Exam.builder()
                .startDate(LocalDateTime.of(2025, 2, 10, 9, 0))
                .endDate(LocalDateTime.of(2025, 2, 10, 12, 0))
                .build();
        examService.save(exam);
        examService.save(exam1);


        // Prove Registration for teachers
        managerService.proofRegisteredTeacher("maryamsadidi");
        managerService.proofRegisteredTeacher("mohamadzaki");


        // Prove Registration for student
        managerService.proofRegisteredStudent("yasnahoseini");
        managerService.proofRegisteredStudent("salmaashrafi");
        managerService.proofRegisteredStudent("helmashojaei");


        //Add teacher to Course
        managerService.addTeacherToCourse(1L, 3L);
        managerService.addTeacherToCourse(2L, 2L);


        //Add student to Course
        managerService.addStudentToCourse(4L, 1L);
        managerService.addStudentToCourse(5L, 1L);
        managerService.addStudentToCourse(6L, 2L);

        // Add exam by teacher
        teacherService.addExamByTeacher(2L,
                LocalDateTime.of(2025, 4, 10, 13, 40),
                LocalDateTime.of(2025, 4, 10, 15, 30));
        teacherService.addExamByTeacher(3L,
                LocalDateTime.of(2025, 6, 8, 10, 15),
                LocalDateTime.of(2025, 6, 8, 11, 45));


        // Add exam to course
        teacherService.addExamToCourseByTeacherId(3L, 1L, 1L);
        teacherService.addExamToCourseByTeacherId(3L, 1L, 3L);
        teacherService.addExamToCourseByTeacherId(2L, 2L, 2L);
        teacherService.addExamToCourseByTeacherId(2L, 2L, 4L);


        // Add Question to Exam
        teacherService.addQuestionToExamByTeacherFromBankQuestion(2L, 2L, 1L , 12.0);
        teacherService.addQuestionToExamByTeacherFromBankQuestion(2L, 2L, 2L , 15.0);
        teacherService.addQuestionToExamByTeacherFromBankQuestion(3L, 3L, 3L , 16.0);
        teacherService.addQuestionToExamByTeacherFromBankQuestion(3L, 3L, 4L , 8.5);


        // Add Answer ToDQ
        answerToDQService.answerToDQ(4L, 1L, "AAAAAAAAAAAAA");
        answerToMCQService.answerToMCQ(5L, 3L, 3L,2 );



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