package dev.jeremidigitallab.learn.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import dev.jeremidigitallab.learn.dto.CreateProductRequest;
import dev.jeremidigitallab.learn.dto.DataWithPage;
import dev.jeremidigitallab.learn.dto.GetAllProductRequest;
import dev.jeremidigitallab.learn.dto.GetProductByIdRequest;
import dev.jeremidigitallab.learn.dto.ProductStandardDto;
import dev.jeremidigitallab.learn.dto.SearchProductByNameRequest;
import dev.jeremidigitallab.learn.dto.UpdateProductRequest;
import dev.jeremidigitallab.learn.entity.Category;
import dev.jeremidigitallab.learn.entity.Product;
import dev.jeremidigitallab.learn.exception.DataNotFoundException;
import dev.jeremidigitallab.learn.repository.CategoryRepository;
import dev.jeremidigitallab.learn.repository.CustomRepository;
import dev.jeremidigitallab.learn.repository.ProductRepository;

@Service
public class ProductService {

	@Autowired
	ProductRepository productRepository;

	@Autowired
	CategoryRepository categoryRepository;

	@Autowired
	CustomRepository customRepository;

	public ProductStandardDto createProduct(CreateProductRequest request) throws DataNotFoundException {

		// validate category
		Optional<Category> categoryInDb = categoryRepository.findById(request.getCategoryId());

		if (categoryInDb.isEmpty()) {
			throw new DataNotFoundException("category is not found");
		}

		Category category = categoryInDb.get();

		// Save To DB
		Product newProduct = new Product();
		newProduct.setProductId(UUID.randomUUID().toString());
		newProduct.setProductName(request.getProductName());
		newProduct.setProductDescription(request.getProductDescription());
		newProduct.setCategoryId(category.getCategoryId());
		newProduct.setCreatedDate(new Date());
		newProduct.setLastModified(new Date());

		newProduct = productRepository.save(newProduct);

		return new ProductStandardDto(newProduct, category);
	}

	public DataWithPage<List<ProductStandardDto>> getAllProduct(GetAllProductRequest request) {

		if (request.getPage() < 0) {
			request.setPage(0);
		}

		Pageable page = PageRequest.of(request.getPage() - 1, 5);
		Page<Product> productInDb = productRepository.findAll(page);

		List<ProductStandardDto> productDto = new ArrayList<>();

		productInDb.forEach(data -> {
			productDto.add(new ProductStandardDto(data));
		});

		DataWithPage<List<ProductStandardDto>> data = new DataWithPage<>();
		data.setCurrentPage(request.getPage());
		data.setPreviousPage(request.getPage() - 1);
		data.setNextPage(request.getPage() + 1);
		data.setTotalPage(productInDb.getTotalPages());
		data.setData(productDto);

		return data;

	}

	public ProductStandardDto getProductById(GetProductByIdRequest request) throws DataNotFoundException {

		Optional<Product> productInDb = productRepository.findById(request.getProductId());

		if (productInDb.isEmpty()) {
			throw new DataNotFoundException("product not found");
		}

		Product product = productInDb.get();

		Optional<Category> categoryInDb = categoryRepository
				.findById(product.getCategoryId());

		if (categoryInDb.isEmpty()) {
			throw new DataNotFoundException("category not found");
		}

		return new ProductStandardDto(product, categoryInDb.get());
	}

	public List<ProductStandardDto> searchProductByName(SearchProductByNameRequest request) throws SQLException {

		List<ProductStandardDto> productDto = new ArrayList<>();

		customRepository
				.searchProductByName(request.getProductName())
				.forEach(data -> productDto.add(new ProductStandardDto(data)));

		return productDto;

	}

	public ProductStandardDto updateProduct(UpdateProductRequest request) throws DataNotFoundException {

		Optional<Product> productInDb = productRepository.findById(request.getProductId());

		if (productInDb.isEmpty()) {
			throw new DataNotFoundException("product not found");
		}

		Product product = productInDb.get();

		product.setProductName(request.getProductName());
		product.setProductDescription(request.getProductDescription());
		product.setCategoryId(request.getCategoryId());
		product.setLastModified(new Date());

		product = productRepository.save(product);

		Optional<Category> categoryInDb = categoryRepository
				.findById(product.getCategoryId());

		if (categoryInDb.isEmpty()) {
			throw new DataNotFoundException("category not found");
		}

		return new ProductStandardDto(product, categoryInDb.get());

	}

}
