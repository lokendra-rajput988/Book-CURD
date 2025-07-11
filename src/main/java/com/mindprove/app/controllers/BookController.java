package com.mindprove.app.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mindprove.app.dtos.BookDto;
import com.mindprove.app.response.PagedResponse;
import com.mindprove.app.response.ResponseDto;
import com.mindprove.app.services.IBookServices;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/book")
public class BookController {
	
	
	Logger log=LoggerFactory.getLogger(BookController.class);
	
	@Autowired
	private IBookServices bookService;
	
	@PostMapping("/")
	public ResponseEntity<ResponseDto> createBook(@Valid @RequestBody BookDto bookDto ){
		log.info("Post API called : {}");
		return  ResponseEntity.ok().body(bookService.createBook(bookDto));
		
	}
	
	@GetMapping("/{bookId}")
	public ResponseEntity<ResponseDto> getBookById(@PathVariable("bookId") Long bookId){
		log.info("Get API called : {}");
		return  ResponseEntity.ok().body(bookService.getBookById(bookId));
		
	}
	
	@GetMapping("/")
	public ResponseEntity<PagedResponse<BookDto>> getAllBooks(@RequestParam(name = "pageNumber",defaultValue = "0")Integer pageNumber,
			@RequestParam(name="pageSize",defaultValue = "5")Integer pageSize,
			@RequestParam(name="title",defaultValue = "title")String sortBy,
			@RequestParam(name="ascending" ,defaultValue = "true")boolean ascending
			){
		log.info("Get API called : {}");
		return  ResponseEntity.ok().body(bookService.getAllBooks(pageNumber, pageSize, sortBy,ascending));
	}
	
	@GetMapping("/search")
	public ResponseEntity<ResponseDto> searchBooks(@RequestParam(name="keyword") String keyword){
		log.info("Get API called : {}");
		return ResponseEntity.ok().body(bookService.searchBookByTitleOrAuthor(keyword,keyword));
	}
	
	@PutMapping("/{bookId}")
	public ResponseEntity<ResponseDto> updateBookById(@PathVariable("bookId") Long bookId,@Valid @RequestBody BookDto bookDto ){
		log.info("Put API called : {}");
		return  ResponseEntity.ok().body(bookService.updateBook(bookDto, bookId));
		
	}
	
	@DeleteMapping("/{bookId}")
	public ResponseEntity<ResponseDto> deleteBookById(@PathVariable("bookId") Long bookId ){
		log.info("Delete API called : {}");
		return  ResponseEntity.ok().body(bookService.deleteBookById(bookId));
		
	}
	
	
	
	

}
