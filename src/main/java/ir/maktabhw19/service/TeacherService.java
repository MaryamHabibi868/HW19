package ir.maktabhw19.service;


import ir.maktabhw19.domains.Teacher;
import ir.maktabhw19.service.base.BaseService;

import java.util.Optional;

public interface TeacherService
        extends BaseService<Teacher, Long> {

     Optional<Teacher> findTeacherByUserName(String userName);
}
