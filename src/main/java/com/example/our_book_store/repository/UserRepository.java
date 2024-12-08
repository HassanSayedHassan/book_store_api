package com.example.our_book_store.repository;

import com.example.our_book_store.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, String> {

    Optional<User> findByEmailAndPassword(String email, String password);
}
