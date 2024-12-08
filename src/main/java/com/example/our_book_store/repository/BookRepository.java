package com.example.our_book_store.repository;

import com.example.our_book_store.models.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Cart, Long> {

}
