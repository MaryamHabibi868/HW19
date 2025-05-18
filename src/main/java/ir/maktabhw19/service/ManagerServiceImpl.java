package ir.maktabhw19.service;

import ir.maktabhw19.domains.Manager;
import ir.maktabhw19.repository.ManagerRepository;
import ir.maktabhw19.service.base.BaseServiceImpl;

public class ManagerServiceImpl
extends BaseServiceImpl<Manager, Long, ManagerRepository>
        implements ManagerService{

    public ManagerServiceImpl(ManagerRepository repository) {
        super(repository);
    }


}
