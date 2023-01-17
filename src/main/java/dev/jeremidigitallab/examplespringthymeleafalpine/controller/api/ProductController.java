package dev.jeremidigitallab.examplespringthymeleafalpine.controller.api;

import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import dev.jeremidigitallab.examplespringthymeleafalpine.dto.CreateProductRequest;
import dev.jeremidigitallab.examplespringthymeleafalpine.dto.GetAllProductRequest;
import dev.jeremidigitallab.examplespringthymeleafalpine.dto.GetProductByIdRequest;
import dev.jeremidigitallab.examplespringthymeleafalpine.dto.SearchProductByNameRequest;
import dev.jeremidigitallab.examplespringthymeleafalpine.dto.SimpleResponse;
import dev.jeremidigitallab.examplespringthymeleafalpine.dto.UpdateProductRequest;
import dev.jeremidigitallab.examplespringthymeleafalpine.exception.DataNotFoundException;
import dev.jeremidigitallab.examplespringthymeleafalpine.service.ProductService;

@RestController
@RequestMapping("/api/v1/product")
public class ProductController {

	private static final String PRODUCT = "product";

	@Autowired
	ProductService productService;

	@PostMapping
	public ResponseEntity<SimpleResponse> createProduct(@RequestBody CreateProductRequest request)
			throws DataNotFoundException {

		Map<String, Object> payload = new LinkedHashMap<>();
		payload.put(PRODUCT, productService.createProduct(request));

		return SimpleResponse.created(payload);
	}

	@GetMapping
	public ResponseEntity<SimpleResponse> getAllProduct(@RequestParam(name = "page") Integer page) {

		Map<String, Object> payload = new LinkedHashMap<>();
		GetAllProductRequest request = new GetAllProductRequest(page);
		payload.put(PRODUCT, productService.getAllProduct(request));

		return SimpleResponse.ok(payload);
	}

	@GetMapping("/{id}")
	public ResponseEntity<SimpleResponse> getProductById(@PathVariable(name = "id") String productId)
			throws DataNotFoundException {

		Map<String, Object> payload = new LinkedHashMap<>();
		GetProductByIdRequest request = new GetProductByIdRequest(productId);
		payload.put(PRODUCT, productService.getProductById(request));

		return SimpleResponse.ok(payload);
	}

	@GetMapping("/search")
	public ResponseEntity<SimpleResponse> searchProductByName(@RequestParam(name = "name") String name)
			throws SQLException {

		Map<String, Object> payload = new LinkedHashMap<>();
		SearchProductByNameRequest request = new SearchProductByNameRequest(name);
		payload.put(PRODUCT, productService.searchProductByName(request));

		return SimpleResponse.ok(payload);

	}

	@PostMapping("/update")
	public ResponseEntity<SimpleResponse> updateProduct(@RequestBody UpdateProductRequest request)
			throws DataNotFoundException {

		Map<String, Object> payload = new LinkedHashMap<>();
		payload.put(PRODUCT, productService.updateProduct(request));

		return SimpleResponse.ok(payload);
	}

}
