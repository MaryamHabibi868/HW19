package ir.maktabhw19.service;

import ir.maktabhw19.domains.*;
import ir.maktabhw19.repository.ManagerRepository;
import ir.maktabhw19.service.base.BaseServiceImpl;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public class ManagerServiceImpl
        extends BaseServiceImpl<Manager, Long, ManagerRepository>
        implements ManagerService {

    public ManagerServiceImpl(ManagerRepository repository,
                              TeacherService teacherService,
                              StudentService studentService,
                              CourseService courseService) {
        super(repository);
        this.teacherService = teacherService;
        this.studentService = studentService;
        this.courseService = courseService;
    }


    private final TeacherService teacherService;
    private final StudentService studentService;
    private final CourseService courseService;


    @Override
    public Optional<Manager> findManagerByUserName(String userName) {
        if (repository.findByUserName(userName).isEmpty()) {
            throw new RuntimeException("Teacher not found");
        }
        return repository.findByUserName(userName);
    }

    @Override
    public void registerManager(String firstName, String lastName,
                                String userName, String password) {
        repository.beginTransaction();
        Manager manager = new Manager();
        manager.setFirstName(firstName);
        manager.setLastName(lastName);
        manager.setUserName(userName);
        manager.setPassword(password);
        repository.save(manager);
        repository.commitTransaction();
        System.out.println("Manager registered successfully");
    }

    @Override
    public void loginManager(String username, String password) {
        repository.beginTransaction();
        Manager manager = new Manager();
        Optional<Manager> foundManager = repository.findByUserName(username);
        if (foundManager.isPresent()) {
            if (foundManager.get().getPassword().equals(password)) {
                System.out.println("Manager logged in successfully");
                repository.commitTransaction();
            } else {
                System.out.println("Wrong password");
            }
        } else {
            System.out.println("userName not found");
        }
    }

    @Override
    public void proofRegisteredTeacher(String userName) {
        repository.beginTransaction();
        Optional<Teacher> foundTeacher = teacherService.findTeacherByUserName(userName);
        if (foundTeacher.isPresent()) {
            Teacher teacher = foundTeacher.get();
            if (teacher.getRegistrationConfirmation() == RegistrationConfirmation.PENDING) {
                teacher.setRegistrationConfirmation(RegistrationConfirmation.APPROVED);
                teacherService.save(teacher);
                repository.commitTransaction();
                System.out.println("Teacher registration APPROVED by manager successfully");
            } else {
                System.out.println("Teacher registration has already been approved");
            }
        }
    }

    @Override
    public void proofRegisteredStudent(String userName) {
        repository.beginTransaction();
        Optional<Student> foundStudent = studentService.findStudentByUserName(userName);
        if (foundStudent.isPresent()) {
            Student student = foundStudent.get();
            if (student.getRegistrationConfirmation() == RegistrationConfirmation.PENDING) {
                student.setRegistrationConfirmation(RegistrationConfirmation.APPROVED);
                studentService.save(student);
                repository.commitTransaction();
                System.out.println("Student registration APPROVED by manager successfully");
            } else {
                System.out.println("Student registration has already been approved");
            }

        }
    }

    @Override
    public List<Course> printAllCourses() {
        return courseService.findAll();
    }

    @Override
    public List<Teacher> printAllTeachers() {
        return teacherService.findAll();
    }

    @Override
    public List<Student> printAllStudents() {
        return studentService.findAll();
    }

    @Override
    public void printUsersByCourseID(Long courseId) {
        repository.beginTransaction();
        if (courseService.findById(courseId).isEmpty()) {
            throw new RuntimeException("Course not found");
        }
        Course course = courseService.findById(courseId).get();
        System.out.println("This is the List of Teachers in this course:");
        System.out.println(course.getTeacher());
        System.out.println("*****************************************************");
        System.out.println("This is the List of Students in this course:");
        System.out.println(course.getStudents());
        repository.commitTransaction();
    }


    @Override
    public void addCourse(String title, LocalDate startDate, LocalDate endDate) {
        repository.beginTransaction();
        if (courseService.findByTitle(title).isPresent()) {
            courseService.findByTitle(title).get().setStartDate(startDate);
            courseService.findByTitle(title).get().setEndDate(endDate);
            courseService.save(courseService.findByTitle(title).get());
            System.out.println("Course edited successfully");
        } else {
            Course course = new Course();
            course.setTitle(title);
            course.setStartDate(startDate);
            course.setEndDate(endDate);
            courseService.save(course);
            System.out.println("Course" + title + " added successfully");
        }
        repository.commitTransaction();
    }

    @Override
    public void removeCourse(Long courseId) {
        repository.beginTransaction();
        if (courseService.findById(courseId).isEmpty()) {
            System.out.println("Course not found");
        }
        Course course = courseService.findById(courseId).get();
        course.setStudents(null);
        course.setTeacher(null);
        courseService.save(course);
        courseService.deleteById(courseId);
        repository.commitTransaction();
        System.out.println("Course removed successfully");
    }

    @Override
    public void addTeacherToCourse(Long courseId, Long teacherId) {
        repository.beginTransaction();
        if (courseService.findById(courseId).isEmpty()) {
            throw new RuntimeException("CourseId " + courseId + " not found");
        }
        if (teacherService.findById(teacherId).isEmpty()) {
            throw new RuntimeException("TeacherId " + teacherId + " not found");
        }
        Course course = courseService.findById(courseId).get();
        course.setTeacher(teacherService.findById(teacherId).get());
        courseService.save(course);
        teacherService.save(teacherService.findById(teacherId).get());
        repository.commitTransaction();
        System.out.println("Teacher added to this course " + courseId + " successfully");
    }

    @Override
    public void removeTeacherFromCourse(Long courseId, Long teacherId) {
        repository.beginTransaction();
        if (courseService.findById(courseId).isEmpty()) {
            throw new RuntimeException("CourseId " + courseId + " not found");
        }
        if (teacherService.findById(teacherId).isEmpty()) {
            throw new RuntimeException("TeacherId " + teacherId + " not found");
        }
        Course course = courseService.findById(courseId).get();
        course.setTeacher(null);
        courseService.save(course);
        teacherService.save(teacherService.findById(teacherId).get());
        repository.commitTransaction();
        System.out.println("Teacher removed from this course " + courseId + " successfully");
    }

    @Override
    public void addStudentToCourse(Long studentId, Long courseId) {
        repository.beginTransaction();
        if (courseService.findById(courseId).isEmpty()) {
            throw new RuntimeException("CourseId " + courseId + " not found");
        }
        if (studentService.findById(studentId).isEmpty()) {
            throw new RuntimeException("StudentId " + studentId + " not found");
        }
        Course course = courseService.findById(courseId).get();
        course.getStudents().add(studentService.findById(studentId).get());
        courseService.save(course);
        studentService.save(studentService.findById(studentId).get());
        repository.commitTransaction();
        System.out.println("Student added to this course " + courseId + " successfully");
    }

    @Override
    public void removeStudentFromCourse(Long studentId, Long courseId) {
        repository.beginTransaction();
        if (courseService.findById(courseId).isEmpty()) {
            throw new RuntimeException("CourseId " + courseId + " not found");
        }
        if (studentService.findById(studentId).isEmpty()) {
            throw new RuntimeException("StudentId " + studentId + " not found");
        }
        Course course = courseService.findById(courseId).get();
        course.getStudents().remove(studentService.findById(studentId).get());
        courseService.save(course);
        studentService.save(studentService.findById(studentId).get());
        repository.commitTransaction();
        System.out.println("Student removed from this course " + courseId + " successfully");
    }

    @Override
    public void changeStudentToTeacher(Long studentId) {
        repository.beginTransaction();
        if (studentService.findById(studentId).isEmpty()) {
            throw new RuntimeException("StudentId " + studentId + " not found");
        }
        Teacher teacher = new Teacher();
        teacher.setFirstName(studentService.findById(studentId).get().getFirstName());
        teacher.setLastName(studentService.findById(studentId).get().getLastName());
        teacher.setUserName(studentService.findById(studentId).get().getUserName());
        teacher.setPassword(studentService.findById(studentId).get().getPassword());
        teacherService.save(teacher);
        repository.commitTransaction();
        System.out.println("Student changed to teacher successfully");
    }

    @Override
    public void changeTeacherToStudent(Long teacherId) {
        repository.beginTransaction();
        if (teacherService.findById(teacherId).isEmpty()) {
            throw new RuntimeException("TeacherId " + teacherId + " not found");
        }
        Student student = new Student();
        student.setFirstName(teacherService.findById(teacherId).get().getFirstName());
        student.setLastName(teacherService.findById(teacherId).get().getLastName());
        student.setUserName(teacherService.findById(teacherId).get().getUserName());
        student.setPassword(teacherService.findById(teacherId).get().getPassword());
        studentService.save(student);
        repository.commitTransaction();
        System.out.println("Teacher changed to Student successfully");
    }
}
