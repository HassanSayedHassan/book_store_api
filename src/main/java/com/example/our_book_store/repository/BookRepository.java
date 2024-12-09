package com.example.our_book_store.repository;

import com.example.our_book_store.models.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {

}
