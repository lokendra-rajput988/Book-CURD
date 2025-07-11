package com.mindprove.app.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.mindprove.app.annotations.ValidAuthor;
import com.mindprove.app.annotations.ValidName;
import com.mindprove.app.annotations.ValidPrice;
import com.mindprove.app.annotations.ValidTitle;
import com.mindprove.app.annotations.ValidYear;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(name="book")
public class BookDb {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long bookId;
	
	@Column(name="book_name")
	private String bookName;
	
	@Column(name = "title")
	private String title;
	
	@Column(name = "author")
	private String author;
	
	@Column(name = "publication_year")
	private Integer publicationYear;
	
	@Column(name="price")
	private Double price;
	
	@ManyToOne
	@JsonIgnore
	private PublisherDb publisher;
}
