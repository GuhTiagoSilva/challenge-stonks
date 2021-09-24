package com.stonks.fiap.challengestonks.repositories;

import com.stonks.fiap.challengestonks.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
}
