package dev.jeremidigitallab.learn.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import dev.jeremidigitallab.learn.entity.Category;

public interface CategoryRepository extends PagingAndSortingRepository<Category, String> {

}
