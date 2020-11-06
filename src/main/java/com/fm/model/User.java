package com.fm.model;

import org.springframework.security.core.authority.SimpleGrantedAuthority;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(min = 2, message = "Minimum firstname length: 2 characters")
    @Column(nullable = false)
    private String firstName;

    @Size(min = 2, message = "Minimum firstname length: 2 characters")
    @Column(nullable = false)
    private String lastName;

    @Size(min = 4, max = 15, message = "Minimum username length: 4 characters")
    @Column(unique = true, nullable = false)
    private String username;

    @Size(min = 8, message = "Minimum password length: 8 characters")
    private String password;

    @Column(unique = true, nullable = false)
    private String email;

    @ElementCollection(fetch = FetchType.EAGER)
    private List<Role> roles = new ArrayList<>();

    private boolean active;

    private LocalDate registrationDate;

    public User() {
        this.active = true;
        this.registrationDate = LocalDate.now();
    }

    // TODO: remove setters in prod

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public LocalDate getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(LocalDate registrationDate) {
        this.registrationDate = registrationDate;
    }

    // helper methods

    public List<SimpleGrantedAuthority> getAuthorities() {
        return roles.stream()
            .map(role -> new SimpleGrantedAuthority(role.getAuthority()))
            .filter(simpleGrantedAuthority -> Objects.nonNull(simpleGrantedAuthority))
            .collect(Collectors.toList());
    }

    @Override
    public String toString() {
        return "User{" +
            "id=" + id +
            ", firstName='" + firstName + '\'' +
            ", lastName='" + lastName + '\'' +
            ", username='" + username + '\'' +
            ", password='" + password + '\'' +
            ", email='" + email + '\'' +
            ", roles=" + roles +
            ", active=" + active +
            ", registrationDate=" + registrationDate +
            '}';
    }
}
