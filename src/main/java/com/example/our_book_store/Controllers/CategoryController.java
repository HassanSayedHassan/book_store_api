package com.example.our_book_store.Controllers;

import com.example.our_book_store.Services.category.ICategoryService;
import com.example.our_book_store.models.Category;
import com.example.our_book_store.models.dto.UserResponseDto;
import com.example.our_book_store.models.dto.UserSignUpDto;
import com.example.our_book_store.responces.ApiResponse;
import com.example.our_book_store.responces.PaginatedApiResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/category")
@AllArgsConstructor
public class CategoryController {
    private final ICategoryService categoryService;


    @PostMapping()
    public ResponseEntity<ApiResponse> addCategory(@RequestParam String categoryName) {
        Category savedCategory = categoryService.addCategory(categoryName);
        return ResponseEntity.ok().body(new ApiResponse("Category created successfully", savedCategory));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse> updateCategory(@RequestParam String categoryName, @PathVariable Long id) {
        Category updatedCategory = categoryService.updateCategory(id, categoryName);
        return ResponseEntity.ok().body(new ApiResponse("Category Updated successfully", updatedCategory));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteCategory(@PathVariable Long id) {
        categoryService.deleteCategory(id);
        return ResponseEntity.ok().body(new ApiResponse("Category deleted successfully", null));
    }

    @GetMapping()
    public ResponseEntity<PaginatedApiResponse<Category>> getCategory(@RequestParam(defaultValue = "10") int limit,
                                                                      @RequestParam(defaultValue = "0") int offset) {
        var response = categoryService.getCategory(limit, offset);
        return ResponseEntity.ok().body(response);
    }
}
