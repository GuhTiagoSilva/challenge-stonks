package com.stonks.fiap.challengestonks.repositories;

import com.stonks.fiap.challengestonks.entities.Teacher;
import com.stonks.fiap.challengestonks.entities.User;
import com.stonks.fiap.challengestonks.tests.Factory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;

import java.util.List;
import java.util.Optional;

@DataJpaTest
public class TeacherRepositoryTest {

    @Autowired
    private TeacherRepository repository;

    private long existingId;
    private long nonExistingId;
    private long countTotalTeachers;

    @BeforeEach
    void setUp() throws Exception {
        countTotalTeachers = 1L;
        existingId = 2L;
        nonExistingId = 4000L;
    }

    @Test
    public void findAllShouldReturnListOfTeachers() {
        List<Teacher> teachers = repository.findAll();
        Assertions.assertNotNull(teachers);
        Assertions.assertEquals(teachers.size(), countTotalTeachers);
    }

    @Test
    public void findByIdShouldReturnTeacherWhenIdExists() {
        Optional<Teacher> teacher = repository.findById(existingId);
        Assertions.assertFalse(teacher.isEmpty());
        Assertions.assertTrue(teacher.isPresent());
    }

    @Test
    public void findByIdShouldReturnEmptyOptionalWhenIdNotExists() {
        Optional<Teacher> teacher = repository.findById(nonExistingId);
        Assertions.assertTrue(teacher.isEmpty());
        Assertions.assertFalse(teacher.isPresent());
    }

    @Test
    public void saveShouldPersistTeacher() {
        Teacher teacher = Factory.createTeacher();
        teacher.setId(null);

        teacher = repository.save(teacher);

        Optional<Teacher> optional = repository.findById(teacher.getId());
        Assertions.assertFalse(optional.isEmpty());
        Assertions.assertTrue(optional.isPresent());
        Assertions.assertSame(optional.get(), teacher);
    }

    @Test
    public void updateShouldUpdateTeacher() {
        Teacher teacher = Factory.createTeacher();
        String firstName = teacher.getFirstName();

        teacher.setFirstName("Gustavo");
        teacher = repository.save(teacher);

        Assertions.assertFalse((teacher.getFirstName().equals(firstName)));
        Assertions.assertTrue(teacher.getFirstName().equals("Gustavo"));
    }

    @Test
    public void deleteShouldThrowEmptyResultDataAccessExceptionWhenIdDoesNotExists() {
        Assertions.assertThrows(EmptyResultDataAccessException.class, () -> {
            repository.deleteById(nonExistingId);
        });
    }

}
