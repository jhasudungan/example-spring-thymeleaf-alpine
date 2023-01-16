
package dev.jeremidigitallab.learn.repository;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;

import dev.jeremidigitallab.learn.entity.Product;

public interface ProductRepository extends PagingAndSortingRepository<Product, String> {

	List<Product> findByProductName(String productName);

}
