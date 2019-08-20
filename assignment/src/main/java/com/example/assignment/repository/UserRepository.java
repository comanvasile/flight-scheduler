package com.example.assignment.repository;

import com.example.assignment.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {
    User findById(final Long id);
    User findByUsername(final String username);
}
