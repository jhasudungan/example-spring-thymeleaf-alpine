package dev.jeremidigitallab.examplespringthymeleafalpine.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class GetCategoryByIdRequest {

	private String categoryId;
}
