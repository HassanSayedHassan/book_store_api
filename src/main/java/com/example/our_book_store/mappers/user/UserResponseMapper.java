package com.example.our_book_store.mappers.user;

import com.example.our_book_store.mappers.Mapper;
import com.example.our_book_store.models.User;
import com.example.our_book_store.models.dto.UserResponseDto;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class UserResponseMapper implements Mapper<UserResponseDto, User> {
    private final ModelMapper modelMapper;

    @Override
    public UserResponseDto toDto(User entity) {
        return modelMapper.map(entity,UserResponseDto.class);
    }

    @Override
    public User toEntity(UserResponseDto dto) {
        return modelMapper.map(dto,User.class);
    }
}
