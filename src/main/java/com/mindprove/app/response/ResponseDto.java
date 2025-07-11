package com.mindprove.app.response;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ResponseDto {
	
	private String message;
	private Object object;
	private HttpStatus httpStatus;
	private LocalDateTime localDateTime;
	
	public ResponseDto(String message,Object object,HttpStatus httpStatus) {
		this.message=message;
		this.object=object;
		this.httpStatus=httpStatus;
		this.localDateTime=LocalDateTime.now();
	}
}
