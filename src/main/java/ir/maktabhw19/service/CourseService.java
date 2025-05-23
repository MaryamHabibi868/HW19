package ir.maktabhw19.service;

import ir.maktabhw19.domains.Course;
import ir.maktabhw19.service.base.BaseService;

import java.util.Optional;

public interface CourseService extends
        BaseService<Course, Long> {

    Optional<Course> findByTitle(String title);
}
