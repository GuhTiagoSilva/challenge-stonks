package com.stonks.fiap.challengestonks.repositories;

import com.stonks.fiap.challengestonks.entities.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeacherRepository extends JpaRepository<Teacher, Long> {
}
