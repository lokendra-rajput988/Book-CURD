package com.mindprove.app.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mindprove.app.dtos.BookDto;
import com.mindprove.app.entities.BookDb;
import com.mindprove.app.repositories.PublisherRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
@RequiredArgsConstructor
public class BookMapper {

	@Autowired
	private PublisherRepository publisherRepository;
	
	public BookDb toEntity(BookDto bookDto) {
		log.info("DTO to Entity");
		BookDb bookDb=new BookDb();
		bookDb.setBookId(bookDto.getBookId());
		bookDb.setBookName(bookDto.getBookName());
		bookDb.setAuthor(bookDto.getAuthor());
		bookDb.setTitle(bookDto.getTitle());
		bookDb.setPrice(bookDto.getPrice());
		bookDb.setPublicationYear(bookDto.getPublicationYear());
		bookDb.setPublisher(publisherRepository.findById(bookDto.getPublisherId()).get());
		return bookDb;
	}
	
	public BookDto toDTO(BookDb bookDb) {
		log.info("Entity to DTO");
		BookDto bookDto=new BookDto();
		bookDto.setBookId(bookDb.getBookId());
		bookDto.setBookName(bookDb.getBookName());
		bookDto.setAuthor(bookDb.getAuthor());
		bookDto.setTitle(bookDb.getTitle());
		bookDto.setPrice(bookDb.getPrice());
		bookDto.setPublicationYear(bookDb.getPublicationYear());
		bookDto.setPublisherId(bookDb.getPublisher().getPublisherId());
		
		return bookDto;
	}
}
