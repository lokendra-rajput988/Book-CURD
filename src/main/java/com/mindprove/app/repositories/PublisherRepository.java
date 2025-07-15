package com.mindprove.app.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mindprove.app.entities.PublisherDb;

public interface PublisherRepository extends JpaRepository<PublisherDb, Long> {
}
