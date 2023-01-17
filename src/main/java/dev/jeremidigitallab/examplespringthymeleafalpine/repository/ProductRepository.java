
package dev.jeremidigitallab.examplespringthymeleafalpine.repository;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;

import dev.jeremidigitallab.examplespringthymeleafalpine.entity.Product;

public interface ProductRepository extends PagingAndSortingRepository<Product, String> {

	List<Product> findByProductName(String productName);

}
