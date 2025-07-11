package com.mindprove.app.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mindprove.app.entities.BookDb;

public interface BookRepository extends JpaRepository<BookDb, Long> {

	 List<BookDb> findByTitleContainingIgnoreCaseOrAuthorContainingIgnoreCase(String title, String author);
}
