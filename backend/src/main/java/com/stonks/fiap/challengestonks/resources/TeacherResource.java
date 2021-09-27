package com.stonks.fiap.challengestonks.resources;

import com.stonks.fiap.challengestonks.dto.TeacherDTO;
import com.stonks.fiap.challengestonks.dto.UserDTO;
import com.stonks.fiap.challengestonks.services.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@CrossOrigin("http://localhost:4200")
@RestController
@RequestMapping(value = "/teachers")
public class TeacherResource {

    @Autowired
    private TeacherService service;

    @GetMapping
    public ResponseEntity<List<TeacherDTO>> findAll() {
        List<TeacherDTO> result = service.findAll();
        return ResponseEntity.ok().body(result);
    }

    @PostMapping
    public ResponseEntity<TeacherDTO> insert (@RequestBody TeacherDTO dto) {
        dto = service.insert(dto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}").buildAndExpand(dto.getId()).toUri();
        return ResponseEntity.created(uri).body(dto);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<TeacherDTO> update (@RequestBody TeacherDTO dto, @PathVariable Long id) {
        dto = service.update(id, dto);
        return ResponseEntity.ok().body(dto);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<TeacherDTO> findById (@PathVariable Long id) {
        TeacherDTO dto = service.findById(id);
        return ResponseEntity.ok().body(dto);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deleteById (@PathVariable Long id) {
        service.deleteById(id);
        return ResponseEntity.noContent().build();
    }




}
