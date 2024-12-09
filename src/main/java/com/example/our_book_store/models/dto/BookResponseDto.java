package com.example.our_book_store.models.dto;


import com.example.our_book_store.models.Category;
import com.example.our_book_store.models.Review;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookResponseDto {

    private Long id;
    private String auther;
    private String photo;
    private Integer popularity;
    private Double price;
    private String title;

//    private List<ReviewRequestDto> reviews= new ArrayList<>();

    private List<Category> categories = new ArrayList<>();

}
