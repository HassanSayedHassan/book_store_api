package com.example.our_book_store.Controllers;


import com.example.our_book_store.Services.UserService;
import com.example.our_book_store.models.User;
import com.example.our_book_store.models.dto.UserLoginDto;
import com.example.our_book_store.models.dto.UserResponseDto;
import com.example.our_book_store.models.dto.UserSignUpDto;
import com.example.our_book_store.responces.ApiResponse;
import com.example.our_book_store.responces.PaginatedApiResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@AllArgsConstructor
public class UserController {
    private final UserService userServices;

    @PostMapping("signup")
    public ResponseEntity<ApiResponse> signUp(@Valid @RequestBody  UserSignUpDto userSignUpDto) {
            UserResponseDto userResponseDto=userServices.signUp(userSignUpDto);
            return ResponseEntity.ok().body(new ApiResponse("User created successfully",userResponseDto));
    }

    @PostMapping("login")
    public ResponseEntity<ApiResponse> login(@RequestBody UserLoginDto userLoginDto) {
            UserResponseDto userResponseDto=userServices.login(userLoginDto);
            return ResponseEntity.ok().body(new ApiResponse("User logged in successfully",userResponseDto));
    }

    @GetMapping()
    public ResponseEntity<PaginatedApiResponse<UserResponseDto>> getUser(
            @RequestParam(defaultValue = "10") int limit,
            @RequestParam(defaultValue = "0") int offset
    ) {
            var response= userServices.getAllUsers(limit,offset);
            return ResponseEntity.ok().body(response);

    }

}
