package com.stonks.fiap.challengestonks.repositories;

import com.stonks.fiap.challengestonks.entities.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
}
