package dev.jeremidigitallab.examplespringthymeleafalpine.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UpdateCategoryRequest {

	private String categoryId;
	private String categoryName;
	private String categoryDescription;

}
