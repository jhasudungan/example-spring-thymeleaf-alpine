package dev.jeremidigitallab.examplespringthymeleafalpine.service;

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

import dev.jeremidigitallab.examplespringthymeleafalpine.dto.CategoryStandardDto;
import dev.jeremidigitallab.examplespringthymeleafalpine.dto.CreateCategoryRequest;
import dev.jeremidigitallab.examplespringthymeleafalpine.dto.DataWithPage;
import dev.jeremidigitallab.examplespringthymeleafalpine.dto.GetAllCategoryRequest;
import dev.jeremidigitallab.examplespringthymeleafalpine.dto.GetCategoryByIdRequest;
import dev.jeremidigitallab.examplespringthymeleafalpine.dto.SearchCategoryByNameRequest;
import dev.jeremidigitallab.examplespringthymeleafalpine.dto.UpdateCategoryRequest;
import dev.jeremidigitallab.examplespringthymeleafalpine.entity.Category;
import dev.jeremidigitallab.examplespringthymeleafalpine.exception.DataNotFoundException;
import dev.jeremidigitallab.examplespringthymeleafalpine.repository.CategoryRepository;
import dev.jeremidigitallab.examplespringthymeleafalpine.repository.CustomRepository;

@Service
public class CategoryService {

	@Autowired
	CategoryRepository categoryRepository;

	@Autowired
	CustomRepository customRepository;

	public CategoryStandardDto createCategory(CreateCategoryRequest request) {

		Category newCategory = new Category();
		newCategory.setCategoryId(UUID.randomUUID().toString());
		newCategory.setCategoryName(request.getCategoryName());
		newCategory.setCategoryDescription(request.getCategoryDescription());
		newCategory.setCreatedDate(new Date());
		newCategory.setLastModified(new Date());

		newCategory = categoryRepository.save(newCategory);

		return new CategoryStandardDto(newCategory);
	}

	public DataWithPage<List<CategoryStandardDto>> getAllCategory(GetAllCategoryRequest request) {

		if (request.getPage() < 0) {
			request.setPage(0);
		}

		Pageable page = PageRequest.of(request.getPage() - 1, 5);
		Page<Category> categoryInDb = categoryRepository.findAll(page);

		List<CategoryStandardDto> categoryDto = new ArrayList<>();

		categoryInDb
				.forEach(data -> categoryDto.add(new CategoryStandardDto(data)));

		DataWithPage<List<CategoryStandardDto>> data = new DataWithPage<>();
		data.setCurrentPage(request.getPage());
		data.setPreviousPage(request.getPage() - 1);
		data.setNextPage(request.getPage() + 1);
		data.setTotalPage(categoryInDb.getTotalPages());
		data.setData(categoryDto);

		return data;
	}

	public CategoryStandardDto getCategoryById(GetCategoryByIdRequest request) throws DataNotFoundException {

		Optional<Category> categoryInDb = categoryRepository.findById(request.getCategoryId());

		if (categoryInDb.isEmpty()) {
			throw new DataNotFoundException("category is not found");
		}

		return new CategoryStandardDto(categoryInDb.get());
	}

	public List<CategoryStandardDto> searchCategoryByName(SearchCategoryByNameRequest request) throws SQLException {

		List<CategoryStandardDto> categoryDto = new ArrayList<>();

		customRepository
				.searchCategoryByName(request.getCategoryName())
				.forEach(data -> categoryDto.add(new CategoryStandardDto(data)));

		return categoryDto;
	}

	public CategoryStandardDto updatecategory(UpdateCategoryRequest request) throws DataNotFoundException {

		Optional<Category> categoryInDb = categoryRepository.findById(request.getCategoryId());

		if (categoryInDb.isEmpty()) {
			throw new DataNotFoundException("category is not found");
		}

		Category category = categoryInDb.get();

		category.setCategoryName(request.getCategoryName());
		category.setCategoryDescription(request.getCategoryDescription());
		category = categoryRepository.save(category);

		return new CategoryStandardDto(category);
	}
}
