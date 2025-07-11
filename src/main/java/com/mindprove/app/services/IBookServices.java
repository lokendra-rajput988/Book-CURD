package com.mindprove.app.services;

import com.mindprove.app.dtos.BookDto;
import com.mindprove.app.response.PagedResponse;
import com.mindprove.app.response.ResponseDto;

public interface IBookServices {

	//Create Book
	public ResponseDto createBook(BookDto bookDto);
	
	//Update Book By ID
	public ResponseDto updateBook(BookDto bookDto,Long bookId);
	
	//Delete Book By ID
	public ResponseDto deleteBookById(Long bookId);
	
	//Get Book By ID
	public ResponseDto getBookById(Long bookId);
	
	//Get All Books
	public PagedResponse<BookDto> getAllBooks(Integer pageNumber,Integer pageSize,String sortBy,boolean ascending );

	public ResponseDto searchBookByTitleOrAuthor(String title, String author); 
	
}
