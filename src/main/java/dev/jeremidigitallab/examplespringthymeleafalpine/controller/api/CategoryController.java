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

import dev.jeremidigitallab.examplespringthymeleafalpine.dto.CreateCategoryRequest;
import dev.jeremidigitallab.examplespringthymeleafalpine.dto.GetAllCategoryRequest;
import dev.jeremidigitallab.examplespringthymeleafalpine.dto.GetCategoryByIdRequest;
import dev.jeremidigitallab.examplespringthymeleafalpine.dto.SearchCategoryByNameRequest;
import dev.jeremidigitallab.examplespringthymeleafalpine.dto.SimpleResponse;
import dev.jeremidigitallab.examplespringthymeleafalpine.dto.UpdateCategoryRequest;
import dev.jeremidigitallab.examplespringthymeleafalpine.exception.DataNotFoundException;
import dev.jeremidigitallab.examplespringthymeleafalpine.service.CategoryService;

@RestController
@RequestMapping("/api/v1/category")
public class CategoryController {

	private static final String CATEGORY = "category";

	@Autowired
	CategoryService categoryService;

	@PostMapping
	public ResponseEntity<SimpleResponse> createCategory(@RequestBody CreateCategoryRequest request) {

		Map<String, Object> payload = new LinkedHashMap<>();
		payload.put(CATEGORY, categoryService.createCategory(request));

		return SimpleResponse.created(payload);
	}

	@GetMapping
	public ResponseEntity<SimpleResponse> getAllCategory(@RequestParam(name = "page") Integer page) {

		GetAllCategoryRequest request = new GetAllCategoryRequest(page);
		Map<String, Object> payload = new LinkedHashMap<>();
		payload.put(CATEGORY, categoryService.getAllCategory(request));

		return SimpleResponse.ok(payload);
	}

	@GetMapping("/{id}")
	public ResponseEntity<SimpleResponse> getCategoryId(@PathVariable(name = "id") String categoryId)
			throws DataNotFoundException {

		GetCategoryByIdRequest request = new GetCategoryByIdRequest(categoryId);
		Map<String, Object> payload = new LinkedHashMap<>();
		payload.put(CATEGORY, categoryService.getCategoryById(request));

		return SimpleResponse.ok(payload);
	}

	@GetMapping("/search")
	public ResponseEntity<SimpleResponse> searchCategory(@RequestParam(name = "name") String name)
			throws SQLException {

		SearchCategoryByNameRequest request = new SearchCategoryByNameRequest(name);
		Map<String, Object> payload = new LinkedHashMap<>();
		payload.put(CATEGORY, categoryService.searchCategoryByName(request));

		return SimpleResponse.ok(payload);
	}

	@PostMapping("/update")
	public ResponseEntity<SimpleResponse> updateCategory(@RequestBody UpdateCategoryRequest request)
			throws DataNotFoundException {

		Map<String, Object> payload = new LinkedHashMap<>();
		payload.put(CATEGORY, categoryService.updatecategory(request));
		return SimpleResponse.ok(payload);
	}

}
