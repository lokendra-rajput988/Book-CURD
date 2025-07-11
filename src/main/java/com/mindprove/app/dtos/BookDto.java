package com.mindprove.app.dtos;






import com.mindprove.app.annotations.ValidAuthor;
import com.mindprove.app.annotations.ValidName;
import com.mindprove.app.annotations.ValidPrice;
import com.mindprove.app.annotations.ValidTitle;
import com.mindprove.app.annotations.ValidYear;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class BookDto {
	
	private Long bookId;
	
	@ValidName
	private String bookName;
	@ValidTitle
	private String title;
	@ValidAuthor
	private String author;
	@ValidYear
	private Integer publicationYear;
	@ValidPrice
	private Double price;
	private Long publisherId;

}
