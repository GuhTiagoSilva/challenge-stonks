package com.stonks.fiap.challengestonks.dto;

import com.stonks.fiap.challengestonks.entities.User;

import java.io.Serializable;
import java.time.Instant;

public class UserDTO implements Serializable {

    private Long id;
    private String firstName;
    private String lastName;
    private Instant bornDate;
    private String email;
    private String password;
    private Long roleId;

    public UserDTO() {

    }

    public UserDTO(Long id, String firstName, String lastName, Instant bornDate, String email, String password, Long roleId) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.bornDate = bornDate;
        this.email = email;
        this.password = password;
        this.roleId = roleId;
    }

    public UserDTO (User entity) {
        id = entity.getId();
        firstName = entity.getFirstName();
        lastName = entity.getLastName();
        bornDate = entity.getBornDate();
        email = entity.getEmail();
        password = entity.getPassword();
        roleId = entity.getRole().getId();
    }

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

    public Instant getBornDate() {
        return bornDate;
    }

    public void setBornDate(Instant bornDate) {
        this.bornDate = bornDate;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }
}
