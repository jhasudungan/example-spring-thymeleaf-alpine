package dev.jeremidigitallab.examplespringthymeleafalpine.controller.web;

import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import dev.jeremidigitallab.examplespringthymeleafalpine.dto.CategoryStandardDto;
import dev.jeremidigitallab.examplespringthymeleafalpine.dto.CreateCategoryRequest;
import dev.jeremidigitallab.examplespringthymeleafalpine.dto.GetAllCategoryRequest;
import dev.jeremidigitallab.examplespringthymeleafalpine.dto.GetCategoryByIdRequest;
import dev.jeremidigitallab.examplespringthymeleafalpine.dto.SearchCategoryByNameRequest;
import dev.jeremidigitallab.examplespringthymeleafalpine.dto.UpdateCategoryRequest;
import dev.jeremidigitallab.examplespringthymeleafalpine.exception.DataNotFoundException;
import dev.jeremidigitallab.examplespringthymeleafalpine.service.CategoryService;

@Controller
@RequestMapping("/category")
public class CategoryWebController {

	@Autowired
	CategoryService categoryService;

	@GetMapping("/create")
	public String showCreateCategoryForm(Model model) {
		CreateCategoryRequest createCategoryRequest = new CreateCategoryRequest();
		model.addAttribute("request", createCategoryRequest);
		return "create";
	}

	@GetMapping()
	public String showCategoryList(@RequestParam(name = "page") Integer page, Model model) {
		SearchCategoryByNameRequest newSearchCategoryByNameRequest = new SearchCategoryByNameRequest();
		GetAllCategoryRequest getAllCategoryRequest = new GetAllCategoryRequest(page);
		model.addAttribute("newSearchCategoryByNameRequest", newSearchCategoryByNameRequest);
		model.addAttribute("categories", categoryService.getAllCategory(getAllCategoryRequest));
		return "category-list";
	}

	@GetMapping("/{id}")
	public String showCategoryInformation(@PathVariable(name = "id") String categoryId, Model model)
			throws DataNotFoundException {
		GetCategoryByIdRequest request = new GetCategoryByIdRequest(categoryId);

		// Existing category data
		CategoryStandardDto existingCategoryData = categoryService.getCategoryById(request);

		// Set initial data of "updateCategoryRequest" to existingCategoryData
		UpdateCategoryRequest updateCategoryRequest = new UpdateCategoryRequest();
		updateCategoryRequest.setCategoryId(existingCategoryData.getCategoryId());
		updateCategoryRequest.setCategoryName(existingCategoryData.getCategoryName());
		updateCategoryRequest.setCategoryDescription(existingCategoryData.getCategoryDescription());

		// bind model to view
		model.addAttribute("updateCategoryRequest", updateCategoryRequest);
		return "category-info";
	}

	@PostMapping("/create/save")
	public String handleCreateCategoryRequest(@ModelAttribute("request") CreateCategoryRequest request) {
		CategoryStandardDto createdCategory = categoryService.createCategory(request);
		return "redirect:/category/" + createdCategory.getCategoryId();
	}

	@PostMapping("/search")
	public String handleSearchCategoryRequest(Model model,
			@ModelAttribute("newSearchCategoryByNameRequest") SearchCategoryByNameRequest newSearchCategoryByNameRequest)
			throws SQLException {
		model.addAttribute("newSearchCategoryByNameRequest", newSearchCategoryByNameRequest);
		model.addAttribute("categories", categoryService.searchCategoryByName(newSearchCategoryByNameRequest));
		return "category-search-result";
	}

	@PostMapping("/update/save")
	public String handleUpdateCatgoryRequest(@ModelAttribute("updateCategoryRequest") UpdateCategoryRequest request)
			throws DataNotFoundException {
		CategoryStandardDto updatedCategory = categoryService.updatecategory(request);
		return "redirect:/category/" + updatedCategory.getCategoryId();
	}

}
