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

import dev.jeremidigitallab.examplespringthymeleafalpine.dto.CreateProductRequest;
import dev.jeremidigitallab.examplespringthymeleafalpine.dto.GetAllProductRequest;
import dev.jeremidigitallab.examplespringthymeleafalpine.dto.GetProductByIdRequest;
import dev.jeremidigitallab.examplespringthymeleafalpine.dto.ProductStandardDto;
import dev.jeremidigitallab.examplespringthymeleafalpine.dto.SearchProductByNameRequest;
import dev.jeremidigitallab.examplespringthymeleafalpine.dto.UpdateProductRequest;
import dev.jeremidigitallab.examplespringthymeleafalpine.exception.DataNotFoundException;
import dev.jeremidigitallab.examplespringthymeleafalpine.service.CategoryService;
import dev.jeremidigitallab.examplespringthymeleafalpine.service.ProductService;

@Controller
@RequestMapping("/product")
public class ProductWebController {

	@Autowired
	ProductService productService;

	@Autowired
	CategoryService categoryService;

	@GetMapping
	public String showProductList(@RequestParam(name = "page") Integer page, Model model) {
		GetAllProductRequest request = new GetAllProductRequest(page);
		SearchProductByNameRequest newSearchProductByNameRequest = new SearchProductByNameRequest();
		model.addAttribute("products", productService.getAllProduct(request));
		model.addAttribute("newSearchProductByNameRequest", newSearchProductByNameRequest);
		return "product-list";
	}

	@GetMapping("/create")
	public String showCreateProductForm(Model model) {
		CreateProductRequest request = new CreateProductRequest();
		model.addAttribute("request", request);
		return "create-product";
	}

	@GetMapping("/{id}")
	public String showProductInformation(@PathVariable(name = "id") String productId, Model model)
			throws DataNotFoundException {
		GetProductByIdRequest request = new GetProductByIdRequest(productId);
		ProductStandardDto productData = productService.getProductById(request);

		// Set initial value of updateProductRequest as existing product data
		UpdateProductRequest updateProductRequest = new UpdateProductRequest();
		updateProductRequest.setProductId(productData.getProductId());
		updateProductRequest.setProductDescription(productData.getProductDescription());
		updateProductRequest.setProductName(productData.getProductName());
		updateProductRequest.setCategoryId(productData.getCategory().getCategoryId());
		updateProductRequest.setCategoryName(productData.getCategory().getCategoryName());

		model.addAttribute("updateProductRequest", updateProductRequest);
		return "product-info";
	}

	@PostMapping("/create/save")
	public String handleCreateProductRequest(@ModelAttribute("request") CreateProductRequest request)
			throws DataNotFoundException {
		ProductStandardDto createdProduct = productService.createProduct(request);
		return "redirect:/product/" + createdProduct.getProductId();
	}

	@PostMapping("/update/save")
	public String handleUpdateProductReqeust(@ModelAttribute("updateProductRequest") UpdateProductRequest request)
			throws DataNotFoundException {
		ProductStandardDto updatedProduct = productService.updateProduct(request);
		return "redirect:/product/" + updatedProduct.getProductId();
	}

	@PostMapping("/search")
	public String handleSearchProductRequest(
			@ModelAttribute("newSearchProductByNameRequest") SearchProductByNameRequest newSearchProductByNameRequest,
			Model model) throws SQLException {
		model.addAttribute("newSearchProductByNameRequest", newSearchProductByNameRequest);
		model.addAttribute("products", productService.searchProductByName(newSearchProductByNameRequest));
		return "product-search-result";
	}

}
