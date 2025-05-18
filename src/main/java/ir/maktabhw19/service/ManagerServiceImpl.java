package ir.maktabhw19.service;

import ir.maktabhw19.domains.Manager;
import ir.maktabhw19.repository.ManagerRepository;
import ir.maktabhw19.service.base.BaseServiceImpl;

public class ManagerServiceImpl
        extends BaseServiceImpl<Manager, Long, ManagerRepository>
        implements ManagerService {

    public ManagerServiceImpl(ManagerRepository repository) {
        super(repository);
    }

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

   /* public void loginManager(String username, String password) {
        repository.beginTransaction();
        Manager manager = new Manager();
        manager.
    }*/
}
