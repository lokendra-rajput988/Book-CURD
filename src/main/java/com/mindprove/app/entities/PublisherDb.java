package com.mindprove.app.entities;

import java.util.ArrayList;
import java.util.List;

import com.mindprove.app.annotations.ValidName;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(name="publisher")
@SequenceGenerator(name="publisherSequence",sequenceName = "publisher_id",allocationSize = 1)
public class PublisherDb {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "publisherSequence")
	private Long publisherId;
	
	@Column(name="name")
	private String name;
	
	@OneToMany(mappedBy = "publisher", cascade = CascadeType.ALL)
	private List<BookDb> books=new ArrayList<>();
}
