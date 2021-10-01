package com.stonks.fiap.challengestonks.services;

import com.stonks.fiap.challengestonks.dto.CourseDTO;
import com.stonks.fiap.challengestonks.entities.Course;
import com.stonks.fiap.challengestonks.entities.Role;
import com.stonks.fiap.challengestonks.entities.User;
import com.stonks.fiap.challengestonks.repositories.CourseRepository;
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
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.times;

@ExtendWith(SpringExtension.class)
public class CourseServiceTests {

    @InjectMocks
    private CourseService courseService;

    @Mock
    private CourseRepository repository;

    private Course course;
    private long existingId;
    private long nonExistingId;
    private long dependentId;

    @BeforeEach
    void setUp() throws Exception {

        existingId = 1L;
        nonExistingId = 2L;
        dependentId = 3L;

        course = Factory.createCourse();
        Mockito.doThrow(EntityNotFoundException.class).when(repository).getById(nonExistingId);
        Mockito.when(repository.findAll()).thenReturn(List.of(course));
        Mockito.when(repository.save(ArgumentMatchers.any())).thenReturn(course);
        Mockito.when(repository.findById(existingId)).thenReturn(Optional.of(course));
        Mockito.when(repository.findById(nonExistingId)).thenReturn(Optional.empty());
        Mockito.doNothing().when(repository).deleteById(existingId);
        Mockito.doThrow(EmptyResultDataAccessException.class).when(repository).deleteById(nonExistingId);
        Mockito.doThrow(DataIntegrityViolationException.class).when(repository).deleteById(dependentId);
    }

    @Test
    public void findByIdShouldThrowsResourceNotFoundExceptionWhenNonExistingId() {
        Assertions.assertThrows(ResourceNotFoundException.class, () -> {
            courseService.findById(nonExistingId);
        });
    }

    @Test
    public void findByIdShouldReturnCourseDTOWhenIdExists() {
        CourseDTO dto = courseService.findById(existingId);
        Assertions.assertNotNull(dto);
    }

    @Test
    public void insertShouldReturnCourseDTOWithValidId() {
        CourseDTO courseDTO = Factory.createCourseDTO();
        courseDTO.setId(null);
        courseDTO = courseService.insert(courseDTO);

        Assertions.assertNotNull(courseDTO.getId());
        Assertions.assertEquals(courseDTO.getId(),  1);
    }

    @Test
    public void updateShouldThrowResourceNotFoundExceptionWhenIdDoesNotExists() {
        CourseDTO dto = Factory.createCourseDTO();
        Assertions.assertThrows(ResourceNotFoundException.class, () -> {
            courseService.update(nonExistingId, dto);
        });
    }

    @Test
    public void deleteShouldThrowResourceNotFoundExceptionWhenIdDoesNotExists() {
        Assertions.assertThrows(ResourceNotFoundException.class, () -> {
            courseService.deleteById(nonExistingId);
        });
    }

    @Test
    public void deleteShouldDoNothingWhenIdExists() {

        Assertions.assertDoesNotThrow(() -> {
            courseService.deleteById(existingId);
        });

        Mockito.verify(repository, times(1)).deleteById(existingId);
    }
}
