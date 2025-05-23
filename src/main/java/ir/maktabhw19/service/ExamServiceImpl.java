package ir.maktabhw19.service;

import ir.maktabhw19.domains.Exam;
import ir.maktabhw19.repository.ExamRepository;
import ir.maktabhw19.service.base.BaseServiceImpl;

public class ExamServiceImpl
        extends BaseServiceImpl<Exam, Long, ExamRepository>
        implements ExamService {

    public ExamServiceImpl(ExamRepository repository) {
        super(repository);
    }
}
