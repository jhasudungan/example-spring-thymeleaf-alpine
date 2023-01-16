package dev.jeremidigitallab.learn.dto;

import java.util.Date;

import dev.jeremidigitallab.learn.entity.Category;
import dev.jeremidigitallab.learn.entity.Product;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductStandardDto {

	private String productId;
	private String productName;
	private String productDescription;
	private Date createdDate;
	private Date lastModified;
	private CategoryStandardDto category;
	
	// Map entity to dto
	public ProductStandardDto(Product product) {
		this.productId = product.getProductId();
		this.productName = product.getProductName();
		this.productDescription = product.getProductDescription();
		this.createdDate = product.getCreatedDate();
		this.lastModified = product.getLastModified();
	}
	
	public ProductStandardDto(Product product, Category category) {
		this.productId = product.getProductId();
		this.productName = product.getProductName();
		this.productDescription = product.getProductDescription();
		this.createdDate = product.getCreatedDate();
		this.lastModified = product.getLastModified();
		this.category = new CategoryStandardDto(category);
	}

}
