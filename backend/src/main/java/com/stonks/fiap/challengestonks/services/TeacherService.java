package com.stonks.fiap.challengestonks.services;

import com.stonks.fiap.challengestonks.dto.StudentDTO;
import com.stonks.fiap.challengestonks.dto.TeacherDTO;
import com.stonks.fiap.challengestonks.entities.Role;
import com.stonks.fiap.challengestonks.entities.Student;
import com.stonks.fiap.challengestonks.entities.Teacher;
import com.stonks.fiap.challengestonks.repositories.RoleRepository;
import com.stonks.fiap.challengestonks.repositories.TeacherRepository;
import com.stonks.fiap.challengestonks.services.exceptions.DatabaseException;
import com.stonks.fiap.challengestonks.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TeacherService {

    @Autowired
    private TeacherRepository repository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Transactional(readOnly = true)
    public Page<TeacherDTO> findAllPaged(Pageable pageable) {
        Page<Teacher> list = repository.findAll(pageable);
        return list.map(x -> new TeacherDTO(x));
    }

    @Transactional(readOnly = true)
    public List<TeacherDTO> findAll(){
        List<Teacher> list = repository.findAll().stream().filter(x -> x.getRole().getId() == 2).collect(Collectors.toList());
        return list.stream().map(teacher -> new TeacherDTO(teacher)).collect(Collectors.toList());
    }

    @Transactional
    public TeacherDTO insert (TeacherDTO dto) {
        Teacher entity = new Teacher();
        copyDtoToEntity(dto, entity);
        entity.setPassword(passwordEncoder.encode(dto.getPassword()));
        entity = repository.save(entity);
        return new TeacherDTO(entity);
    }

    @Transactional
    public TeacherDTO update (Long id, TeacherDTO dto) {
        try {
            Teacher entity = repository.getById(id);
            copyDtoToEntity(dto, entity);

            entity = repository.save(entity);
            return new TeacherDTO(entity);
        } catch(EntityNotFoundException e) {
            throw new ResourceNotFoundException("Id Not Found: " + id);
        }
    }

    @Transactional(readOnly = true)
    public TeacherDTO findById (Long id) {
        Optional<Teacher> result = repository.findById(id);
        Teacher entity = result.orElseThrow(() -> new ResourceNotFoundException("Teacher Not Found: " + id));
        return new TeacherDTO(entity);
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

    private void copyDtoToEntity(TeacherDTO dto, Teacher entity) {
        entity.setYearsOfExperience(dto.getYearsOfExperience());
        entity.setBornDate(dto.getBornDate());
        entity.setEmail(dto.getEmail());
        entity.setFirstName(dto.getFirstName());
        entity.setLastName(dto.getLastName());
        Role role = roleRepository.getById(dto.getRoleId());
        validateRole(role, entity, dto.getRoleId());
    }

    private void validateRole(Role role, Teacher entity, Long roleId) {
        if (role != null) {
            entity.setRole(role);
        } else {
            throw new ResourceNotFoundException("Role not found: " + roleId);
        }
    }

}
