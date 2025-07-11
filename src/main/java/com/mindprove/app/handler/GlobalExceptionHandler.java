package com.mindprove.app.handler;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;

import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.mindprove.app.exceptions.ResourceNotFoundException;
import com.mindprove.app.response.ResponseDto;

@RestControllerAdvice
public class GlobalExceptionHandler {
	
	Logger logger=LoggerFactory.getLogger(GlobalExceptionHandler.class);
	
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<ResponseDto> resourceNotFoundException(ResourceNotFoundException ex){
		logger.info("ResourceNotFoundException method call");
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ResponseDto(ex.getMessage(), null, HttpStatus.INTERNAL_SERVER_ERROR));
		
	}
	
	 @ExceptionHandler(MethodArgumentNotValidException.class)
	    public ResponseEntity<Map<String, String>> handleValidationErrors(MethodArgumentNotValidException ex) {
	        Map<String, String> errors = new HashMap<>();
	        ex.getBindingResult().getFieldErrors().forEach(error ->
	            errors.put(error.getField(), error.getDefaultMessage())
	        );
	        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
	    }

}
