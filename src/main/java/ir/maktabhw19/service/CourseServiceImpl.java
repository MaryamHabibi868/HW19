package ir.maktabhw19.service;

import ir.maktabhw19.domains.Course;
import ir.maktabhw19.repository.CourseRepository;
import ir.maktabhw19.service.base.BaseServiceImpl;

import java.util.Optional;

public class CourseServiceImpl
        extends BaseServiceImpl<Course, Long, CourseRepository>
        implements CourseService {

    public CourseServiceImpl(CourseRepository repository) {
        super(repository);
    }


    @Override
    public Optional<Course> findByTitle(String title) {
        if (repository.findByTitle(title).isEmpty()) {
           throw new RuntimeException("Title not found");
        }
        return repository.findByTitle(title);
    }

    public void addCourse(Course course) {
        repository.save(course);
    }
}
