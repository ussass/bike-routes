package ru.trofimov.bikeroutes.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.trofimov.bikeroutes.model.User;
import ru.trofimov.bikeroutes.service.UserService;

@Service
public class UserDetailServiceIml implements UserDetailsService {

    private final UserService userService;

    @Autowired
    public UserDetailServiceIml(UserService userService) {
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        User user = userService.findByLogin(login);
        return SecurityUser.fromUser(user);
    }
}
