package com.example.our_book_store.repository;

import com.example.our_book_store.models.Review;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<Review, Long> {

}
