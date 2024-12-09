package com.example.our_book_store.repository;

import com.example.our_book_store.models.Cart;
import com.example.our_book_store.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category, Long> {

    Optional<Category> findByCategoryName(String categoryName);
}
