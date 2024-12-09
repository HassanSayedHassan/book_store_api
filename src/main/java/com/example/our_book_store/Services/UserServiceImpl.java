package com.example.our_book_store.Services;

import com.example.our_book_store.exceptions.ResourceNotFoundException;
import com.example.our_book_store.mappers.UserResponseMapper;
import com.example.our_book_store.mappers.UserSignupMapper;
import com.example.our_book_store.models.User;
import com.example.our_book_store.models.dto.UserLoginDto;
import com.example.our_book_store.models.dto.UserResponseDto;
import com.example.our_book_store.models.dto.UserSignUpDto;
import com.example.our_book_store.repository.UserRepository;
import com.example.our_book_store.responces.PaginatedApiResponse;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@AllArgsConstructor
@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserSignupMapper userSignupMapper;
    private final UserResponseMapper userResponseMapper;

    @Override
    public UserResponseDto signUp(UserSignUpDto userSignUpDto) {
        if (userRepository.findByUserName(userSignUpDto.getUserName()).isPresent()) {
            throw new IllegalArgumentException("Username is already taken");
        }

        // Validate if the email is unique
        if (userRepository.findByEmail(userSignUpDto.getEmail()).isPresent()) {
            throw new IllegalArgumentException("Email is already taken");
        }
        User user = userSignupMapper.toEntity(userSignUpDto);
        user.setId(user.getEmail()); // special case from requirements
        userRepository.save(user);
        return userResponseMapper.toDto(user);
    }

    @Override
    public UserResponseDto login(UserLoginDto userLoginDto) {
        Optional<User> user = userRepository.findByEmailAndPassword(userLoginDto.getEmail(), userLoginDto.getPassword());
        if (user.isEmpty()) throw new ResourceNotFoundException("User not found");
        return userResponseMapper.toDto(user.get());
    }

    @Override
    public PaginatedApiResponse<UserResponseDto> getAllUsers(int limit, int offset) {
        Pageable pageable = PageRequest.of(offset / limit, limit);
        Page<User> users = userRepository.findAll(pageable);
        return new PaginatedApiResponse<UserResponseDto>("Get Users Successfully",
                users.getContent().stream().map(userResponseMapper::toDto).toList(), users.getTotalElements(),users.getTotalPages());
    }
}
