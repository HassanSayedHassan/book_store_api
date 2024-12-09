package com.example.our_book_store.models.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookRequestDto {

    private String auther;
    private String photo;
    private Integer popularity;
    private Double price;
    private String title;

}
