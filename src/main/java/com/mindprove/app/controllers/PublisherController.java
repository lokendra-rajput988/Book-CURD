package com.mindprove.app.controllers;



import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

import com.mindprove.app.dtos.PublisherDto;
import com.mindprove.app.response.PagedResponse;
import com.mindprove.app.response.ResponseDto;
import com.mindprove.app.services.IPublisherService;
import com.mindprove.app.util.constant.Constant;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/publisher/")
public class PublisherController {
	
	Logger log=LoggerFactory.getLogger(PublisherController.class);
	
	@Autowired
	private IPublisherService publisherService;
	
	@PostMapping("createPublisher")
	public ResponseEntity<ResponseDto> createPublisher(@Valid @RequestBody PublisherDto publisherDto) {
		log.info("Post API method call : {}");
		return ResponseEntity.ok().body(new ResponseDto("Success",publisherService.createPublisher(publisherDto),HttpStatus.CREATED));
	}
	
	@PutMapping("updatePublisherById/{publisherId}")
	public ResponseEntity<ResponseDto> updatePublisherById(@Valid @RequestBody PublisherDto publisherDto,@PathVariable("publisherId") Long publisherId){
		log.info("Put API method call : {}");
		return ResponseEntity.ok().body(new ResponseDto("Success",publisherService.updatePublisher(publisherDto,publisherId),HttpStatus.OK));
	}
	
	@GetMapping("getPublisherById/{publisherId}")
	public ResponseEntity<ResponseDto> getPublisherById(@PathVariable("publisherId") Long publisherId){
		log.info("Get API method call : {}");
		return ResponseEntity.ok().body(new ResponseDto("Success",publisherService.getPublisherById(publisherId),HttpStatus.OK));
	}
	
	@GetMapping("getAllPublisher")
	public ResponseEntity<ResponseDto> getAllPublishers(
			@RequestParam(name="pageNumber",defaultValue = "0")int pageNumber,
			@RequestParam(name="pageSize",defaultValue = "pageSize")int pageSize,
			@RequestParam(name="sortBy",defaultValue = "name")String sortBy,
			@RequestParam(name="ascending",defaultValue = "true")boolean ascending
			){
		log.info("Get API method call : {}");
		return ResponseEntity.ok().body(publisherService.getAllPublisher(pageNumber,pageSize,sortBy,ascending));
	}
	
	@DeleteMapping("deletePublisherById/{publisherId}")
	public ResponseEntity<ResponseDto> deletePublisherById(@PathVariable("publisherId") Long publisherId){
		log.info("Delete API method call : {}");
		return ResponseEntity.ok().body(new ResponseDto("Success",publisherService.deletePublisherById(publisherId),HttpStatus.OK));
	}
}
