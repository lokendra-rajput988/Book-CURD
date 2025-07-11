package com.mindprove.app.services;



import com.mindprove.app.dtos.PublisherDto;
import com.mindprove.app.response.PagedResponse;
import com.mindprove.app.response.ResponseDto;

public interface IPublisherService {

	ResponseDto createPublisher(PublisherDto publisherDto);

	ResponseDto getPublisherById(Long publisherId);

	ResponseDto deletePublisherById(Long publisherId);

	ResponseDto getAllPublisher(int pageNumber, int pageSize, String sortBy, boolean ascending);

	ResponseDto updatePublisher(PublisherDto publisherDto, Long publisherId);
}
