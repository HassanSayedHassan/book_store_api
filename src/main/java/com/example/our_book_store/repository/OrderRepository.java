package com.example.our_book_store.repository;

import com.example.our_book_store.models.Order;
import com.example.our_book_store.models.Review;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {

}
