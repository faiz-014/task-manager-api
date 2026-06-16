package com.Faiz.tasks.repositories;

import com.Faiz.tasks.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> getByEmail(String email);
    boolean existsByEmail(String email);
}
