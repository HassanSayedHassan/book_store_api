package com.example.our_book_store.Services;


import com.example.our_book_store.models.User;
import com.example.our_book_store.models.dto.UserLoginDto;
import com.example.our_book_store.models.dto.UserResponseDto;
import com.example.our_book_store.models.dto.UserSignUpDto;
import com.example.our_book_store.responces.PaginatedApiResponse;

import java.util.List;


public interface UserService {

    UserResponseDto signUp(UserSignUpDto userSignUpDto);

    UserResponseDto login(UserLoginDto userLoginDto);

    PaginatedApiResponse<UserResponseDto> getAllUsers(int limit, int offset);
}
