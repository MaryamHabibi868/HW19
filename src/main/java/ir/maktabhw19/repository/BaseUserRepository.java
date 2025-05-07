package ir.maktabhw19.repository;

import ir.maktabhw19.domains.User;

import java.util.Optional;

public interface BaseUserRepository<T extends User> extends CrudRepository<T , Long> {

    Optional<T> findByUsername(String username);

    Optional<T> findByUsernameAndPassword(String username, String password);

}
