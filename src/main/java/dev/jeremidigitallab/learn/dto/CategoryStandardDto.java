package dev.jeremidigitallab.learn.dto;

import java.util.Date;

import dev.jeremidigitallab.learn.entity.Category;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CategoryStandardDto {

	private String categoryId;
	private String categoryName;
	private String categoryDescription;
	private Date createdDate;
	private Date lastModified;
	
	public CategoryStandardDto(Category category) {
		this.categoryId = category.getCategoryId();
		this.categoryName = category.getCategoryName();
		this.categoryDescription = category.getCategoryDescription();
		this.createdDate = category.getCreatedDate();
		this.lastModified = category.getLastModified();
	}
}
