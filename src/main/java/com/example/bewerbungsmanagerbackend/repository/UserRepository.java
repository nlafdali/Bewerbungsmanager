package com.example.bewerbungsmanagerbackend.repository;

import com.example.bewerbungsmanagerbackend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String user);
}
