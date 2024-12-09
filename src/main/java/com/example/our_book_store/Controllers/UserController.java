package com.example.our_book_store.Controllers;


import com.example.our_book_store.Services.user.IUserService;
import com.example.our_book_store.models.dto.UserLoginDto;
import com.example.our_book_store.models.dto.UserResponseDto;
import com.example.our_book_store.models.dto.UserSignUpDto;
import com.example.our_book_store.responces.ApiResponse;
import com.example.our_book_store.responces.PaginatedApiResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@AllArgsConstructor
public class UserController {
    private final IUserService IUserServices;

    @PostMapping("signup")
    public ResponseEntity<ApiResponse> signUp(@Valid @RequestBody  UserSignUpDto userSignUpDto) {
            UserResponseDto userResponseDto= IUserServices.signUp(userSignUpDto);
            return ResponseEntity.ok().body(new ApiResponse("User created successfully",userResponseDto));
    }

    @PostMapping("login")
    public ResponseEntity<ApiResponse> login(@RequestBody UserLoginDto userLoginDto) {
            UserResponseDto userResponseDto= IUserServices.login(userLoginDto);
            return ResponseEntity.ok().body(new ApiResponse("User logged in successfully",userResponseDto));
    }

    @GetMapping()
    public ResponseEntity<PaginatedApiResponse<UserResponseDto>> getUser(
            @RequestParam(defaultValue = "10") int limit,
            @RequestParam(defaultValue = "0") int offset
    ) {
            var response= IUserServices.getAllUsers(limit,offset);
            return ResponseEntity.ok().body(response);

    }

}
