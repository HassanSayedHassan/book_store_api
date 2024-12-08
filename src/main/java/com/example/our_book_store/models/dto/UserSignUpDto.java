package com.example.our_book_store.models.dto;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserSignUpDto {

    private String userName;

    private String email;

    private String password;

    private String phone;

    private String address;

    private String role;
}
