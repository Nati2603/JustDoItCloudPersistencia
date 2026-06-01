package teccr.justdoitcloud.repository;

import org.springframework.data.repository.CrudRepository;
import teccr.justdoitcloud.data.User;

import java.util.Optional;

/**
 * Repository interface for User-related operations.
 */
public interface UserRepository extends  CrudRepository<User, Long> {

    /**
     * Find a user by their username.
     *
     * @param username the username to look up
     * @return an Optional containing the User if found, otherwise empty
     */
    Optional<User> findByUsername(String username);

}
