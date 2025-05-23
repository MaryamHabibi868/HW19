package ir.maktabhw19.repository;

import ir.maktabhw19.domains.Course;
import ir.maktabhw19.repository.base.CrudRepository;

import java.util.Optional;

public interface CourseRepository extends CrudRepository <Course, Long> {

    Optional<Course> findByTitle(String title);
}
