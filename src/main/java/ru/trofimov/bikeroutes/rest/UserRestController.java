package ru.trofimov.bikeroutes.rest;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.trofimov.bikeroutes.exception.InvalidRequestBodyException;
import ru.trofimov.bikeroutes.model.Role;
import ru.trofimov.bikeroutes.model.User;
import ru.trofimov.bikeroutes.security.Token;
import ru.trofimov.bikeroutes.security.jwt.JwtProvider;
import ru.trofimov.bikeroutes.service.UserService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/users")
public class UserRestController {

    private final UserService userService;

    private final JwtProvider jwtProvider;

    public UserRestController(UserService userService, JwtProvider jwtProvider) {
        this.userService = userService;
        this.jwtProvider = jwtProvider;
    }

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {

        List<User> list = userService.findAll();
        List<User> listWithOutPassword = list.stream()
                .peek(user -> user.setPassword(null))
                .collect(Collectors.toList());

        return new ResponseEntity<>(listWithOutPassword, HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity<Token> login(@RequestBody User user) {
        HttpHeaders httpHeaders = new HttpHeaders();

        User authUser = userService.findByLoginAndPassword(user.getLogin(), user.getPassword());
        Token token = new Token(jwtProvider.generateToken(authUser.getLogin()));
        return new ResponseEntity<>(token, httpHeaders, HttpStatus.OK);
    }

    @PostMapping("/signup")
    public ResponseEntity<Token> signup(@RequestBody User user) {
        HttpHeaders httpHeaders = new HttpHeaders();

        User newUser = userService.findByLogin(user.getLogin());
        if (newUser != null) {
            throw new InvalidRequestBodyException("User with this login already exists");
        }
        user.setRole(Role.USER);
        user = userService.save(user);

        Token token = new Token(jwtProvider.generateToken(user.getLogin()));
        return new ResponseEntity<>(token, httpHeaders, HttpStatus.CREATED);
    }
}
