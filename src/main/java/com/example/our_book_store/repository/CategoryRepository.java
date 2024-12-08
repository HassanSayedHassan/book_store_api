package com.example.our_book_store.repository;

import com.example.our_book_store.models.Cart;
import com.example.our_book_store.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {

}
