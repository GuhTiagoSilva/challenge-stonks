package com.stonks.fiap.challengestonks.services;

import com.stonks.fiap.challengestonks.dto.StudentDTO;
import com.stonks.fiap.challengestonks.entities.Role;
import com.stonks.fiap.challengestonks.entities.Student;
import com.stonks.fiap.challengestonks.repositories.RoleRepository;
import com.stonks.fiap.challengestonks.repositories.StudentRepository;
import com.stonks.fiap.challengestonks.repositories.StudentRepository;
import com.stonks.fiap.challengestonks.services.exceptions.ResourceNotFoundException;
import com.stonks.fiap.challengestonks.tests.Factory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.times;

@ExtendWith(SpringExtension.class)
public class StudentServiceTests {

    @InjectMocks
    private StudentService service;

    @Mock
    private StudentRepository repository;

    @Mock
    private BCryptPasswordEncoder passwordEncoder;

    @Mock
    private RoleRepository roleRepository;

    private Student teacher;
    private long existingId;
    private long nonExistingId;
    private long dependentId;

    @BeforeEach
    void setUp() throws Exception {

        existingId = 1L;
        nonExistingId = 2L;
        dependentId = 3L;

        teacher = Factory.createStudent();
        Mockito.when(roleRepository.getById(existingId)).thenReturn(new Role());
        Mockito.doThrow(EntityNotFoundException.class).when(repository).getById(nonExistingId);
        Mockito.when(repository.findAll()).thenReturn(List.of(teacher));
        Mockito.when(repository.save(ArgumentMatchers.any())).thenReturn(teacher);
        Mockito.when(repository.findById(existingId)).thenReturn(Optional.of(teacher));
        Mockito.when(repository.findById(nonExistingId)).thenReturn(Optional.empty());
        Mockito.doNothing().when(repository).deleteById(existingId);
        Mockito.doThrow(EmptyResultDataAccessException.class).when(repository).deleteById(nonExistingId);
        Mockito.doThrow(DataIntegrityViolationException.class).when(repository).deleteById(dependentId);
    }

    @Test
    public void findByIdShouldThrowsResourceNotFoundExceptionWhenNonExistingId() {
        Assertions.assertThrows(ResourceNotFoundException.class, () -> {
            service.findById(nonExistingId);
        });
    }

    @Test
    public void findByIdShouldReturnStudentDTOWhenIdExists() {
        StudentDTO dto = service.findById(existingId);
        Assertions.assertNotNull(dto);
    }

    @Test
    public void insertShouldReturnStudentDTOWithValidId() {
        StudentDTO teacherDTO = Factory.createStudentDTO();
        teacherDTO.setId(null);
        teacherDTO = service.insert(teacherDTO);

        Assertions.assertNotNull(teacherDTO.getId());
        Assertions.assertEquals(teacherDTO.getId(),  1);
    }

    @Test
    public void updateShouldThrowResourceNotFoundExceptionWhenIdDoesNotExists() {
        StudentDTO dto = Factory.createStudentDTO();
        Assertions.assertThrows(ResourceNotFoundException.class, () -> {
            service.update(nonExistingId, dto);
        });
    }

    @Test
    public void deleteShouldThrowResourceNotFoundExceptionWhenIdDoesNotExists() {
        Assertions.assertThrows(ResourceNotFoundException.class, () -> {
            service.deleteById(nonExistingId);
        });
    }

    @Test
    public void deleteShouldDoNothingWhenIdExists() {

        Assertions.assertDoesNotThrow(() -> {
            service.deleteById(existingId);
        });

        Mockito.verify(repository, times(1)).deleteById(existingId);
    }

}
