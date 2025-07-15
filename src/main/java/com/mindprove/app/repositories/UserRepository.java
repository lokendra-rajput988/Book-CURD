package com.mindprove.app.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mindprove.app.entities.UserDb;

public interface UserRepository extends JpaRepository<UserDb, Long> {

	Optional<UserDb> findByEmail(String email);
}
