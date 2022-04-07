package ru.trofimov.bikeroutes.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import ru.trofimov.bikeroutes.model.User;

import java.util.Collection;
import java.util.Set;

public class SecurityUser implements UserDetails {

    private final String username;
    private final String password;
    private final Set<SimpleGrantedAuthority> authorities;

    public SecurityUser(String username, String password, Set<SimpleGrantedAuthority> authorities) {
        this.username = username;
        this.password = password;
        this.authorities = authorities;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public static UserDetails fromUser(User user) {
        if (user == null) {
            return null;
        }
        return new org.springframework.security.core.userdetails.User(
                user.getLogin(), user.getPassword(),
                true,
                true,
                true,
                true,
                user.getAuthorities()
        );
    }
}
