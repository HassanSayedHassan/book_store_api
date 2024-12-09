package com.example.our_book_store.models.dto;


import com.example.our_book_store.models.Book;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReviewRequestDto {
    private String comment;
    private String userEmail;

}
