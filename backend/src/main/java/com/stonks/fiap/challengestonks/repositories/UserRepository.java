package com.stonks.fiap.challengestonks.repositories;

import com.stonks.fiap.challengestonks.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
