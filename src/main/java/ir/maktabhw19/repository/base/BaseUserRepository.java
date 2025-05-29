package ir.maktabhw19.repository.base;

import ir.maktabhw19.domains.User;

import java.util.Optional;

public interface BaseUserRepository<T extends User> extends
        CrudRepository<T , Long> {

    Optional<T> findByUserName(String userName);

    Optional<T> findByUserNameAndPassword(String userName, String password);

}
