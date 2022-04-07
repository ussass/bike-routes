package ru.trofimov.bikeroutes.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String login;

    private String password;

    @Transient
    private Role role;

    @JsonIgnore
    @Transient
    private final Set<SimpleGrantedAuthority> authorities;

    @Column(name = "role")
    private String stringRole;

    public User() {
        authorities = new HashSet<>();
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return stringRole;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public void setStringRole(String stringRole) {
        this.stringRole = stringRole;
    }

    public Set<SimpleGrantedAuthority> getAuthorities() {
        authorities.add(new SimpleGrantedAuthority("ROLE_" + role.name()));
        return authorities;
    }

    public void addRoleFromString() {
        this.role = Role.valueOf(stringRole);
    }

    public void setRoleToStringRoles() {
        stringRole = role.name();
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", role=" + role +
                ", authorities=" + authorities +
                ", stringRole='" + stringRole + '\'' +
                '}';
    }
}
