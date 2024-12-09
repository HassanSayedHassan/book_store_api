package com.example.our_book_store.Services.category;

import com.example.our_book_store.exceptions.ResourceNotFoundException;
import com.example.our_book_store.models.Category;
import com.example.our_book_store.repository.CategoryRepository;
import com.example.our_book_store.responces.PaginatedApiResponse;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@AllArgsConstructor
@Service
public class CategoryServiceImpl implements ICategoryService {
    private final CategoryRepository categoryRepository;


    @Override
    public Category addCategory(String categoryName) {
        // check if category already exists
        Optional<Category> category = categoryRepository.findByCategoryName(categoryName);
        if (category.isPresent()) {
            throw new IllegalArgumentException("Category already exists with id " + category.get().getId());
        }

        Category savedCategory = Category.builder().categoryName(categoryName).build();
        return categoryRepository.save(savedCategory);
    }

    @Override
    public Category updateCategory(Long id, String categoryName) {
        Optional<Category> category = categoryRepository.findById(id);
        if (category.isEmpty()) {
            throw new ResourceNotFoundException("Category not found with id " + id);
        }
        category.get().setCategoryName(categoryName);
        return categoryRepository.save(category.get());
    }

    @Override
    public void deleteCategory(Long id) {
        Category category= categoryRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Category not found with id " + id));

        categoryRepository.delete(category);
        // check if actually deleted
        if (categoryRepository.existsById(id)) {
            throw new IllegalStateException("Category was not successfully deleted. ID: " + id);
        }
    }

    @Override
    public PaginatedApiResponse<Category> getCategory(int limit, int offset) {
        Pageable pageable = PageRequest.of(offset / limit, limit);
        Page<Category> categories = categoryRepository.findAll(pageable);
        return new PaginatedApiResponse<>("Get Categories Successfully", categories.getContent(), categories.getTotalElements(), categories.getTotalPages());
    }
}
