package com.mindprove.app.services.impl;


import java.util.List;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.mindprove.app.dtos.BookDto;
import com.mindprove.app.entities.BookDb;
import com.mindprove.app.entities.PublisherDb;
import com.mindprove.app.exceptions.ResourceNotFoundException;
import com.mindprove.app.repositories.BookRepository;
import com.mindprove.app.repositories.PublisherRepository;
import com.mindprove.app.response.PagedResponse;
import com.mindprove.app.response.ResponseDto;
import com.mindprove.app.services.IBookServices;

@Service
public class BookServicesImpl implements IBookServices {
	
	@Autowired
	private BookRepository bookRepository;
	
	@Autowired
	private PublisherRepository publisherRepository;
	
	@Autowired
	private ModelMapper mapper;
	
	Logger log=LoggerFactory.getLogger(BookServicesImpl.class);
	
	private BookDb dtoToEntity(BookDto bookDto) {
		return mapper.map(bookDto, BookDb.class);
	}
	
	private BookDto entityToDto(BookDb bookDb) {
		return mapper.map(bookDb, BookDto.class);
	}

	@Override
	public ResponseDto createBook(BookDto bookDto) {
		log.info("create book method called");
		PublisherDb publisherDb = publisherRepository.findById(bookDto.getPublisherId()).orElseThrow(()-> new ResourceNotFoundException("ID is not found"));
		BookDb bookDb=dtoToEntity(bookDto);
		bookDb.setPublisher(publisherDb);
		BookDb saveBookDb = bookRepository.save(bookDb);
		log.info("save book inside the database : {}");
		return new ResponseDto("success",entityToDto(saveBookDb), HttpStatus.CREATED);
	}

	@Override
	public ResponseDto updateBook(BookDto bookDto, Long bookId) {
		log.info("update book method called : {}");
		//get BookDb by id
		BookDb bookDb =  bookRepository.findById(bookId).orElseThrow(()-> new ResourceNotFoundException("Id not found"));
		//get PublisherDb by id
		PublisherDb publisherDb = publisherRepository.findById(bookDto.getPublisherId()).orElseThrow(()-> new ResourceNotFoundException("ID is not found"));
		bookDb.setBookName(bookDto.getBookName());
		bookDb.setAuthor(bookDto.getAuthor());
		bookDb.setTitle(bookDto.getTitle());
		bookDb.setPrice(bookDto.getPrice());
		bookDb.setPublicationYear(bookDto.getPublicationYear());
		bookDb.setPublisher(publisherDb);
		BookDb updateBookdb = bookRepository.save(bookDb);
		log.info("updated book inside the database : {}");
		return new ResponseDto("success",entityToDto(updateBookdb), HttpStatus.OK);
	}

	@Override
	public ResponseDto deleteBookById(Long bookId) {
		log.info("delete book method called : {}");
		BookDb bookDb =  bookRepository.findById(bookId).orElseThrow(()-> new ResourceNotFoundException("ID is not found"));
		bookRepository.delete(bookDb);
		return new ResponseDto("success",null,HttpStatus.OK);
	}

	@Override
	public ResponseDto getBookById(Long bookId) {
		log.info("get book method called");
		BookDb bookDb =  bookRepository.findById(bookId).orElseThrow(()-> new ResourceNotFoundException("ID is not found"));
		return new ResponseDto("success",entityToDto(bookDb), HttpStatus.OK);
	}

	@Override
	public PagedResponse<BookDto> getAllBooks(Integer pageNumber, Integer pageSize, String sortBy, boolean ascending) {
		Sort sort = ascending ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(pageNumber, pageSize, sort);
        Page<BookDb> booksPage = bookRepository.findAll(pageable);
        List<BookDto> bookDto = booksPage.stream()
                .map(book -> BookDto.builder()
                		.bookId(book.getBookId())
                		.author(book.getAuthor())
                		.title(book.getTitle())
                		.bookName(book.getBookName())
                		.price(book.getPrice())
                		.publicationYear(book.getPublicationYear())
                		.publisherId(book.getPublisher().getPublisherId())
                        .build())
                .toList();
		 return PagedResponse.from(booksPage, bookDto);
	}

	@Override
	public ResponseDto searchBookByTitleOrAuthor(String title, String author) {
		List<BookDb> bookDb = bookRepository.findByTitleContainingIgnoreCaseOrAuthorContainingIgnoreCase(title, author);
		List<BookDto> bookDto=bookDb.stream()
				.map((book)->BookDto.builder()
				.bookId(book.getBookId())
				.bookName(book.getBookName())
				.author(book.getAuthor())
				.title(book.getTitle())
				.price(book.getPrice())
				.publicationYear(book.getPublicationYear())
				.publisherId(book.getPublisher().getPublisherId())
				.build()
				).toList();
		return new ResponseDto("success", bookDto, HttpStatus.OK);
	}
	

}
