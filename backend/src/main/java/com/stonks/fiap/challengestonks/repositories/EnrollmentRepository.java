package com.stonks.fiap.challengestonks.repositories;

import com.stonks.fiap.challengestonks.entities.Enrollment;
import com.stonks.fiap.challengestonks.entities.pk.EnrollmentPk;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EnrollmentRepository extends JpaRepository<Enrollment, EnrollmentPk> {
}
