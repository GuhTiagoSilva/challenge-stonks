package com.stonks.fiap.challengestonks.tests;

import com.stonks.fiap.challengestonks.dto.UserDTO;
import com.stonks.fiap.challengestonks.entities.Role;
import com.stonks.fiap.challengestonks.entities.User;

import java.time.Instant;

public class Factory {

    public static User createUser() {
        User user = new User(1L, "Henrique", "Lima", Instant.now(), "henrique@gmail.com", "12345678", new Role(1L, null));
        return user;
    }

    public static UserDTO createUserDTO() {
        UserDTO dto = new UserDTO(createUser());
        return dto;
    }

}
