package com.example.our_book_store.repository;

import com.example.our_book_store.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, String> {

    Optional<User> findByEmailAndPassword(String email, String password);
    Optional<User> findByUserName(String userName);
    Optional<User> findByEmail(String email);


}
