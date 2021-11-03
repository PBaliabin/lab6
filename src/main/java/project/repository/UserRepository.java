package project.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import project.model.User;

import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<User, Integer> {

    Optional<User> findUserByLogin(String login);
}
