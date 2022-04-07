package ru.trofimov.bikeroutes.repositories;

import org.springframework.data.repository.CrudRepository;
import ru.trofimov.bikeroutes.model.User;

public interface UserRepository extends CrudRepository<User, Long> {

    User findByLogin(String login);
}
