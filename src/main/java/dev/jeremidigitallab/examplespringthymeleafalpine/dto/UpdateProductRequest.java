package dev.jeremidigitallab.examplespringthymeleafalpine.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UpdateProductRequest {

	private String productId;
	private String productName;
	private String productDescription;
	private String categoryId;
	private String categoryName;

}
