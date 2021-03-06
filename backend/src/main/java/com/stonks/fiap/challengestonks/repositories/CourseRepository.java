package com.stonks.fiap.challengestonks.repositories;

import com.stonks.fiap.challengestonks.entities.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {
}
