package com.mindprove.app.services;



import java.util.List;

import com.mindprove.app.dtos.PublisherDto;
import com.mindprove.app.response.PagedResponse;
import com.mindprove.app.response.ResponseDto;

public interface IPublisherService {

	PublisherDto createPublisher(PublisherDto publisherDto);

	PublisherDto getPublisherById(Long publisherId);

	boolean deletePublisherById(Long publisherId);

	ResponseDto getAllPublisher(int pageNumber, int pageSize, String sortBy, boolean ascending);

	PublisherDto updatePublisher(PublisherDto publisherDto, Long publisherId);
}
