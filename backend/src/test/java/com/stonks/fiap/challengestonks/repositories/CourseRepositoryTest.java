package com.stonks.fiap.challengestonks.repositories;

import com.stonks.fiap.challengestonks.entities.Course;
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
public class CourseRepositoryTest {

    @Autowired
    private CourseRepository repository;

    private long existingId;
    private long nonExistingId;
    private long countTotalCourses;

    @BeforeEach
    void setUp() {
        countTotalCourses = 10L;
        existingId = 1L;
        nonExistingId = 4000L;
    }

    @Test
    public void findAllShouldReturnListOfCourses() {
        List<Course> courses = repository.findAll();
        Assertions.assertNotNull(courses);
        Assertions.assertEquals(courses.size(), countTotalCourses);
    }

    @Test
    public void findByIdShouldReturnCourseWhenIdExists() {
        Optional<Course> course = repository.findById(existingId);
        Assertions.assertFalse(course.isEmpty());
        Assertions.assertTrue(course.isPresent());
    }

    @Test
    public void findByIdShouldReturnEmptyOptionalWhenIdNotExists() {
        Optional<Course> course = repository.findById(nonExistingId);
        Assertions.assertTrue(course.isEmpty());
        Assertions.assertFalse(course.isPresent());
    }

    @Test
    public void saveShouldPersistCourse() {
        Course course = Factory.createCourse();
        course.setId(null);

        course = repository.save(course);

        Optional<Course> optional = repository.findById(course.getId());
        Assertions.assertFalse(optional.isEmpty());
        Assertions.assertTrue(optional.isPresent());
        Assertions.assertSame(optional.get(), course);
    }

    @Test
    public void updateShouldUpdateCourse() {
        Course course = Factory.createCourse();
        String name = course.getName();

        course.setName("C#");
        course = repository.save(course);

        Assertions.assertFalse((course.getName().equals(name)));
        Assertions.assertTrue(course.getName().equals("C#"));
    }

    @Test
    public void deleteShouldDeleteCourseWhenIdExists() {
        repository.deleteById(existingId);
        List<Course> courses = repository.findAll();
        Assertions.assertEquals(courses.size(), countTotalCourses - 1);
    }

    @Test
    public void deleteShouldThrowEmptyResultDataAccessExceptionWhenIdDoesNotExists() {
        Assertions.assertThrows(EmptyResultDataAccessException.class, () -> {
            repository.deleteById(nonExistingId);
        });
    }

}
