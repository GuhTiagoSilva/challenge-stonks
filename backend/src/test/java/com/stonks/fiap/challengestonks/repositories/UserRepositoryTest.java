package com.stonks.fiap.challengestonks.repositories;

import com.stonks.fiap.challengestonks.dto.UserDTO;
import com.stonks.fiap.challengestonks.entities.User;
import com.stonks.fiap.challengestonks.tests.Factory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.dao.EmptyResultDataAccessException;

import java.util.List;
import java.util.Optional;

@DataJpaTest
public class UserRepositoryTest {

    @Autowired
    private UserRepository repository;
    private long existingId;
    private long nonExistingId;
    private long countTotalUsers;

    @BeforeEach
    void setUp() throws Exception {
        countTotalUsers = 2L;
        existingId = 1L;
        nonExistingId = 4000L;
    }

    @Test
    public void findAllShouldReturnListOfUsers() {
        List<User> users = repository.findAll();
        Assertions.assertNotNull(users);
        Assertions.assertEquals(users.size(), countTotalUsers);
    }

    @Test
    public void findByIdShouldReturnUserWhenIdExists() {
        Optional<User> user = repository.findById(existingId);
        Assertions.assertFalse(user.isEmpty());
        Assertions.assertTrue(user.isPresent());
    }

    @Test
    public void findByIdShouldReturnEmptyOptionalWhenIdNotExists() {
        Optional<User> user = repository.findById(nonExistingId);
        Assertions.assertTrue(user.isEmpty());
        Assertions.assertFalse(user.isPresent());
    }

    @Test
    public void saveShouldPersistUser() {
        User user = Factory.createUser();
        user.setId(null);

        user = repository.save(user);

        Optional<User> optional = repository.findById(user.getId());
        Assertions.assertFalse(optional.isEmpty());
        Assertions.assertTrue(optional.isPresent());
        Assertions.assertSame(optional.get(), user);
    }

    @Test
    public void updateShouldUpdateUser() {
        User user = Factory.createUser();
        String firstName = user.getFirstName();

        user.setFirstName("Gustavo");
        user = repository.save(user);

        Assertions.assertFalse((user.getFirstName().equals(firstName)));
        Assertions.assertTrue(user.getFirstName().equals("Gustavo"));
    }

    @Test
    public void deleteShouldDeleteUserWhenIdExists() {
        repository.deleteById(existingId);
        List<User> users = repository.findAll();
        Assertions.assertEquals(users.size(), countTotalUsers - 1);
    }

    @Test
    public void deleteShouldThrowEmptyResultDataAccessExceptionWhenIdDoesNotExists() {
        Assertions.assertThrows(EmptyResultDataAccessException.class, () -> {
            repository.deleteById(nonExistingId);
        });
    }


}
