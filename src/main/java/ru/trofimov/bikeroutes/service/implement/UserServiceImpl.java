package ru.trofimov.bikeroutes.service.implement;

import org.springframework.context.annotation.Lazy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.trofimov.bikeroutes.exception.InvalidRequestBodyException;
import ru.trofimov.bikeroutes.model.User;
import ru.trofimov.bikeroutes.repositories.UserRepository;
import ru.trofimov.bikeroutes.service.UserService;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, @Lazy PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User save(User user) {
        user.setRoleToStringRoles();
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user = userRepository.save(user);
        user.addRoleFromString();
        return user;
    }

    @Override
    @Transactional(readOnly = true)
    public List<User> findAll() {
        return StreamSupport.stream(userRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public User findByLogin(String login) {
        User user = userRepository.findByLogin(login);
        if (user != null) {
            user.addRoleFromString();
        }
        return user;
    }

    @Override
    @Transactional(readOnly = true)
    public User findById(Long id) {
        return userRepository.findById(id).get();
    }

    @Override
    @Transactional
    public void delete(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public User findByLoginAndPassword(String login, String password) {
        User user = findByLogin(login);
        if (user != null) {
            if (passwordEncoder.matches(password, user.getPassword())) {
                return user;
            }
        }
        throw new InvalidRequestBodyException("invalid login or password");
    }
}
