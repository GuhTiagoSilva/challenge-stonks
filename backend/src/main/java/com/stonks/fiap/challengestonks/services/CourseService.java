package com.stonks.fiap.challengestonks.services;

import com.stonks.fiap.challengestonks.dto.CourseDTO;
import com.stonks.fiap.challengestonks.entities.Course;
import com.stonks.fiap.challengestonks.repositories.CourseRepository;
import com.stonks.fiap.challengestonks.services.exceptions.DatabaseException;
import com.stonks.fiap.challengestonks.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CourseService {

    @Autowired
    private CourseRepository repository;


    @Transactional(readOnly = true)
    public Page<CourseDTO> findAllPaged(Pageable pageable) {
        Page<Course> list = repository.findAll(pageable);
        return list.map(x -> new CourseDTO(x));
    }

    @Transactional(readOnly = true)
    public List<CourseDTO> findAll() {
        List<Course> list = repository.findAll();
        return list.stream().map(x -> new CourseDTO(x)).collect(Collectors.toList());
    }

    @Transactional
    public CourseDTO insert (CourseDTO dto) {
        Course entity = new Course();
        copyDtoToEntity(dto, entity);
        entity = repository.save(entity);
        return new CourseDTO(entity);
    }

    @Transactional
    public CourseDTO update (Long id, CourseDTO dto) {
        try {
            Course entity = repository.getById(id);
            copyDtoToEntity(dto, entity);
            entity = repository.save(entity);
            return new CourseDTO(entity);
        } catch(EntityNotFoundException e) {
            throw new ResourceNotFoundException("Id Not Found: " + id);
        }
    }

    @Transactional(readOnly = true)
    public CourseDTO findById (Long id) {
        Optional<Course> result = repository.findById(id);
        Course entity = result.orElseThrow(() -> new ResourceNotFoundException("Course Not Found: " + id));
        return new CourseDTO(entity);
    }

    public void deleteById(Long id) {
        try {
            repository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new ResourceNotFoundException("Id Not Found: " + id);
        } catch (DataIntegrityViolationException e) {
            throw new DatabaseException("DataIntegrityViolationException");
        }
    }


    private void copyDtoToEntity(CourseDTO dto, Course entity) {
        entity.setDescription(dto.getDescription());
        entity.setDuration(dto.getDuration());
        entity.setName(dto.getName());
    }


}
