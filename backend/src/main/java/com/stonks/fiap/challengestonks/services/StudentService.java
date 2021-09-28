package com.stonks.fiap.challengestonks.services;

import com.stonks.fiap.challengestonks.dto.CourseDTO;
import com.stonks.fiap.challengestonks.dto.StudentDTO;
import com.stonks.fiap.challengestonks.entities.Course;
import com.stonks.fiap.challengestonks.entities.Role;
import com.stonks.fiap.challengestonks.entities.Student;
import com.stonks.fiap.challengestonks.entities.User;
import com.stonks.fiap.challengestonks.repositories.RoleRepository;
import com.stonks.fiap.challengestonks.repositories.StudentRepository;
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
public class StudentService {

    @Autowired
    private StudentRepository repository;

    @Autowired
    private RoleRepository roleRepository;

    @Transactional(readOnly = true)
    public Page<StudentDTO> findAll(Pageable pageable) {
        Page<Student> list = repository.findAll(pageable);
        return list.map(x -> new StudentDTO(x));
    }

    @Transactional(readOnly = true)
    public List<StudentDTO> findAll() {
        List<Student> list = repository.findAll().stream().filter(x -> x.getRole().getId() == 1).collect(Collectors.toList());
        return list.stream().map(x -> new StudentDTO(x)).collect(Collectors.toList());
    }

    @Transactional
    public StudentDTO insert (StudentDTO dto) {
        Student entity = new Student();
        copyDtoToEntity(dto, entity);
        entity = repository.save(entity);
        return new StudentDTO(entity);
    }

    @Transactional
    public StudentDTO update (Long id, StudentDTO dto) {
        try {
            Student entity = repository.getById(id);
            copyDtoToEntity(dto, entity);
            entity = repository.save(entity);
            return new StudentDTO(entity);
        } catch(EntityNotFoundException e) {
            throw new ResourceNotFoundException("Id Not Found: " + id);
        }
    }

    @Transactional(readOnly = true)
    public StudentDTO findById (Long id) {
        Optional<Student> result = repository.findById(id);
        Student entity = result.orElseThrow(() -> new ResourceNotFoundException("Student Not Found: " + id));
        return new StudentDTO(entity);
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

    private void copyDtoToEntity(StudentDTO dto, Student entity) {
        entity.setRegistrationCode(dto.getRegistrationCode());
        entity.setBornDate(dto.getBornDate());
        entity.setEmail(dto.getEmail());
        entity.setFirstName(dto.getFirstName());
        entity.setLastName(dto.getLastName());
        entity.setPassword(dto.getPassword());
        Role role = roleRepository.getById(dto.getRoleId());
        validateRole(role, entity, dto.getRoleId());
    }

    private void validateRole(Role role, Student entity, Long roleId) {
        if (role != null) {
            entity.setRole(role);
        } else {
            throw new ResourceNotFoundException("Role not found: " + roleId);
        }
    }


}
