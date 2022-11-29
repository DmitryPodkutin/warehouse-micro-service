package com.gmail.podkutin.dmitry.repository;

import com.gmail.podkutin.dmitry.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByEmail(String email);
}
