package com.stonks.fiap.challengestonks.repositories;

import com.stonks.fiap.challengestonks.entities.Student;
import com.stonks.fiap.challengestonks.entities.Teacher;
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
public class StudentRepositoryTest {

    @Autowired
    private StudentRepository repository;

    private long existingId;
    private long nonExistingId;
    private long countTotalStudents;

    @BeforeEach
    void setUp() throws Exception {
        countTotalStudents = 1L;
        existingId = 2L;
        nonExistingId = 4000L;
    }

    @Test
    public void findAllShouldReturnListOfStudents() {
        List<Student> students = repository.findAll();
        Assertions.assertNotNull(students);
        Assertions.assertEquals(students.size(), countTotalStudents);
    }

    @Test
    public void findByIdShouldReturnStudentWhenIdExists() {
        Optional<Student> student = repository.findById(existingId);
        Assertions.assertFalse(student.isEmpty());
        Assertions.assertTrue(student.isPresent());
    }

    @Test
    public void findByIdShouldReturnEmptyOptionalWhenIdNotExists() {
        Optional<Student> student = repository.findById(nonExistingId);
        Assertions.assertTrue(student.isEmpty());
        Assertions.assertFalse(student.isPresent());
    }

    @Test
    public void saveShouldPersistStudent() {
        Student student = Factory.createStudent();
        student.setId(null);

        student = repository.save(student);

        Optional<Student> optional = repository.findById(student.getId());
        Assertions.assertFalse(optional.isEmpty());
        Assertions.assertTrue(optional.isPresent());
        Assertions.assertSame(optional.get(), student);
    }

    @Test
    public void updateShouldUpdateStudent() {
        Student student = Factory.createStudent();
        String firstName = student.getFirstName();

        student.setFirstName("Gustavo");
        student = repository.save(student);

        Assertions.assertFalse((student.getFirstName().equals(firstName)));
        Assertions.assertTrue(student.getFirstName().equals("Gustavo"));
    }

    @Test
    public void deleteShouldThrowEmptyResultDataAccessExceptionWhenIdDoesNotExists() {
        Assertions.assertThrows(EmptyResultDataAccessException.class, () -> {
            repository.deleteById(nonExistingId);
        });
    }

}
