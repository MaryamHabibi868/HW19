package ir.maktabhw19.service;

import ir.maktabhw19.domains.Course;
import ir.maktabhw19.domains.DescriptiveQuestions;
import ir.maktabhw19.repository.CourseRepository;
import ir.maktabhw19.repository.DescriptiveQuestionsRepository;
import ir.maktabhw19.service.base.BaseServiceImpl;

public class DescriptiveQuestionsServiceImpl
        extends BaseServiceImpl<DescriptiveQuestions, Long,
        DescriptiveQuestionsRepository>
        implements DescriptiveQuestionsService {

    public DescriptiveQuestionsServiceImpl(DescriptiveQuestionsRepository repository) {
        super(repository);
    }
}
