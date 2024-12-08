package com.example.our_book_store.Controllers;


import com.example.our_book_store.Services.UserService;
import com.example.our_book_store.models.User;
import com.example.our_book_store.models.dto.UserLoginDto;
import com.example.our_book_store.models.dto.UserResponseDto;
import com.example.our_book_store.models.dto.UserSignUpDto;
import com.example.our_book_store.responces.ApiResponse;
import com.example.our_book_store.responces.PaginatedApiResponse;
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
    public ResponseEntity<ApiResponse> signUp(@RequestBody  UserSignUpDto userSignUpDto) {
        try {
            UserResponseDto userResponseDto=userServices.signUp(userSignUpDto);
            return ResponseEntity.ok().body(new ApiResponse("User created successfully",userResponseDto));
        }
        catch (Exception e) {
            return ResponseEntity.badRequest().body(new ApiResponse(e.getMessage(),null));
        }
    }

    @PostMapping("login")
    public ResponseEntity<ApiResponse> login(@RequestBody UserLoginDto userLoginDto) {
        try {
            UserResponseDto userResponseDto=userServices.login(userLoginDto);
            return ResponseEntity.ok().body(new ApiResponse("User logged in successfully",userResponseDto));
        }
        catch (Exception e) {
            return ResponseEntity.badRequest().body(new ApiResponse(e.getMessage(),null));
        }
    }

    @GetMapping()
    public ResponseEntity<PaginatedApiResponse<UserResponseDto>> getUser(
            @RequestParam(defaultValue = "10") int limit,
            @RequestParam(defaultValue = "0") int offset
    ) {
        try {
            var response= userServices.getAllUsers(limit,offset);
            return ResponseEntity.ok().body(response);
        }
        catch (Exception e) {
            return ResponseEntity.badRequest().body(new PaginatedApiResponse<>(e.getMessage(),null,0,0));
        }
    }

}
