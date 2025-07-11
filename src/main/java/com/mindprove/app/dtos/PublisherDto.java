package com.mindprove.app.dtos;

import java.util.ArrayList;
import java.util.List;

import com.mindprove.app.annotations.ValidName;
import com.mindprove.app.entities.BookDb;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@NoArgsConstructor
@Data
@AllArgsConstructor
@Builder
public class PublisherDto {

	private Long publisherId;
	@ValidName
	private String name;
	private List<BookDb> books=new ArrayList<>();
}
