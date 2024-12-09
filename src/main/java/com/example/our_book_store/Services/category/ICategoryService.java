package com.example.our_book_store.Services.category;

import com.example.our_book_store.models.Category;
import com.example.our_book_store.responces.PaginatedApiResponse;

public interface ICategoryService {

    Category addCategory(String categoryName);
    Category updateCategory(Long id,String categoryName);

    void deleteCategory(Long id);

    PaginatedApiResponse<Category> getCategory(int limit, int offset);
}
