package com.mindprove.app.mapper;

import org.springframework.stereotype.Component;

import com.mindprove.app.dtos.PublisherDto;
import com.mindprove.app.entities.PublisherDb;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class PublisherMapper {

	public PublisherDb toEntity(PublisherDto publisherDto) {
		log.info("DTO to Entity");
		PublisherDb publisherDb=new PublisherDb();
		publisherDb.setName(publisherDto.getName());
		publisherDb.setBooks(publisherDto.getBooks());
		return publisherDb;
	}
	
	public PublisherDto toDTO(PublisherDb publisherDb) {
		log.info("Entity to DTO");
		PublisherDto publisherDto=new PublisherDto();
		publisherDto.setPublisherId(publisherDb.getPublisherId());
		publisherDto.setName(publisherDb.getName());
		publisherDto.setBooks(publisherDb.getBooks());
		return publisherDto;
	}
	
}
