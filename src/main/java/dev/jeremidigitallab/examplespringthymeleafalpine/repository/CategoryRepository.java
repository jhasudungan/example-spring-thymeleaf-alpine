package dev.jeremidigitallab.examplespringthymeleafalpine.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import dev.jeremidigitallab.examplespringthymeleafalpine.entity.Category;

public interface CategoryRepository extends PagingAndSortingRepository<Category, String> {

}
