package com.mindprove.app.services.impl;



import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.mindprove.app.dtos.PublisherDto;
import com.mindprove.app.entities.PublisherDb;
import com.mindprove.app.exceptions.ResourceNotFoundException;
import com.mindprove.app.mapper.BookMapper;
import com.mindprove.app.mapper.PublisherMapper;
import com.mindprove.app.repositories.BookRepository;
import com.mindprove.app.repositories.PublisherRepository;
import com.mindprove.app.response.PagedResponse;
import com.mindprove.app.response.ResponseDto;
import com.mindprove.app.services.IPublisherService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
@Service
@Slf4j
public class PublisherServiceImpl implements IPublisherService{
	@Autowired
	private PublisherRepository publisherRepository;
	@Autowired
	private BookRepository bookRepository;
	@Autowired
	private PublisherMapper publisherMapper;
	
	
	Logger log=LoggerFactory.getLogger(BookServicesImpl.class);
		
	@Override
	public PublisherDto createPublisher(PublisherDto publisherDto) {
		log.info("create publisher method called");
		PublisherDb publisherDb=publisherMapper.toEntity(publisherDto);
		return publisherMapper.toDTO(publisherRepository.save(publisherDb));
		
	}

	@Override
	public PublisherDto getPublisherById(Long publisherId) {
		log.info("get publisher method called");
		PublisherDb publisherDb=publisherRepository.findById(publisherId).orElseThrow(()-> new ResourceNotFoundException("ID is not found"));
		return publisherMapper.toDTO(publisherDb);
	}

	@Override
	public boolean deletePublisherById(Long publisherId) {
		log.info("delete publisher method called");
		PublisherDb publisherDb=publisherRepository.findById(publisherId).orElseThrow(()-> new ResourceNotFoundException("ID is not found"));
		publisherRepository.delete(publisherDb);
		return true;
		
	}

	@Override
	public ResponseDto getAllPublisher(int pageNumber,int pageSize,String sortBy,boolean ascending) {
		log.info("get all publisher method called : {}");
		 Sort sort=ascending ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
		 Pageable pageable = PageRequest.of(pageNumber, pageSize, sort);
		 Page<PublisherDb> publisherPage = publisherRepository.findAll(pageable);
		 List<PublisherDto> listDto = publisherPage.stream().map((publisher)-> PublisherDto.builder()
		 .publisherId(publisher.getPublisherId())
		 .name(publisher.getName())
		 .books(publisher.getBooks())
		 .build())
		 .toList();
		return new ResponseDto("success",PagedResponse.from(publisherPage, listDto),HttpStatus.OK);
	}

	@Override
	public PublisherDto updatePublisher(PublisherDto publisherDto, Long publisherId) {
		log.info("update publisher method called : {}");
		PublisherDb publisherDb=publisherRepository.findById(publisherId).orElseThrow(()-> new ResourceNotFoundException("ID is not found"));
		publisherDb.setName(publisherDto.getName());
		return publisherMapper.toDTO(publisherRepository.save(publisherDb));
	}

}
