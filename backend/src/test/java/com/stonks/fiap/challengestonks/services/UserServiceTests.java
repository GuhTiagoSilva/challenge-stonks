package com.stonks.fiap.challengestonks.services;

import com.stonks.fiap.challengestonks.dto.UserDTO;
import com.stonks.fiap.challengestonks.entities.Role;
import com.stonks.fiap.challengestonks.entities.User;
import com.stonks.fiap.challengestonks.repositories.RoleRepository;
import com.stonks.fiap.challengestonks.repositories.UserRepository;
import com.stonks.fiap.challengestonks.services.exceptions.ResourceNotFoundException;
import com.stonks.fiap.challengestonks.tests.Factory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.times;

@ExtendWith(SpringExtension.class)
public class UserServiceTests {

    @InjectMocks
    private UserService userService;

    @Mock
    private UserRepository repository;

    @Mock
    private RoleRepository roleRepository;

    @Mock
    private BCryptPasswordEncoder passwordEncoder;

    private User user;
    private long existingId;
    private long nonExistingId;
    private long dependentId;

    @BeforeEach
    void setUp() throws Exception {

        existingId = 1L;
        nonExistingId = 2L;
        dependentId = 3L;

        user = Factory.createUser();
        Mockito.when(roleRepository.getById(existingId)).thenReturn(new Role());
        Mockito.doThrow(EntityNotFoundException.class).when(repository).getById(nonExistingId);
        Mockito.when(repository.findAll()).thenReturn(List.of(user));
        Mockito.when(repository.save(ArgumentMatchers.any())).thenReturn(user);
        Mockito.when(repository.findById(existingId)).thenReturn(Optional.of(user));
        Mockito.when(repository.findById(nonExistingId)).thenReturn(Optional.empty());
        Mockito.doNothing().when(repository).deleteById(existingId);
        Mockito.doThrow(EmptyResultDataAccessException.class).when(repository).deleteById(nonExistingId);
        Mockito.doThrow(DataIntegrityViolationException.class).when(repository).deleteById(dependentId);
    }

    @Test
    public void findByIdShouldThrowsResourceNotFoundExceptionWhenNonExistingId() {
        Assertions.assertThrows(ResourceNotFoundException.class, () -> {
            userService.findById(nonExistingId);
        });
    }

    @Test
    public void findByIdShouldReturnUserDTOWhenIdExists() {
        UserDTO dto = userService.findById(existingId);
        Assertions.assertNotNull(dto);
    }

    @Test
    public void insertShouldReturnUserDTOWithValidId() {
        UserDTO userDTO = Factory.createUserDTO();
        userDTO.setId(null);
        userDTO = userService.insert(userDTO);

        Assertions.assertNotNull(userDTO.getId());
        Assertions.assertEquals(userDTO.getId(),  1);
    }

    @Test
    public void updateShouldThrowResourceNotFoundExceptionWhenIdDoesNotExists() {
        UserDTO dto = Factory.createUserDTO();
        Assertions.assertThrows(ResourceNotFoundException.class, () -> {
           userService.update(dto, nonExistingId);
        });
    }

    @Test
    public void deleteShouldThrowResourceNotFoundExceptionWhenIdDoesNotExists() {
        Assertions.assertThrows(ResourceNotFoundException.class, () -> {
            userService.deleteById(nonExistingId);
        });
    }

    @Test
    public void deleteShouldDoNothingWhenIdExists() {

        Assertions.assertDoesNotThrow(() -> {
            userService.deleteById(existingId);
        });

        Mockito.verify(repository, times(1)).deleteById(existingId);
    }
}
