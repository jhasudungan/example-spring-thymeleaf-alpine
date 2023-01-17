package dev.jeremidigitallab.examplespringthymeleafalpine.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SearchCategoryByNameRequest {

	private String categoryName;

}
