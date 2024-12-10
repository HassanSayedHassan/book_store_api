package com.example.our_book_store.repository;

import com.example.our_book_store.models.Book;
import com.example.our_book_store.models.Cart;
import com.example.our_book_store.models.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<Cart, Long> {

}
