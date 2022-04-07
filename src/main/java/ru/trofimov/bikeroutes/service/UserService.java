package ru.trofimov.bikeroutes.service;

import ru.trofimov.bikeroutes.model.User;

import java.util.List;

public interface UserService {

    User save(User user);

    List<User> findAll();

    User findByLogin(String login);

    User findById(Long id);

    void delete(Long id);

    User findByLoginAndPassword(String login, String password);
}
