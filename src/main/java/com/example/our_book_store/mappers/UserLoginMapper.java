package com.example.our_book_store.mappers;

import com.example.our_book_store.models.User;
import com.example.our_book_store.models.dto.UserResponseDto;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class UserLoginMapper implements Mapper<UserLoginMapper, User> {
    private final ModelMapper modelMapper;

    @Override
    public UserLoginMapper toDto(User entity) {
        return modelMapper.map(entity,UserLoginMapper.class);
    }

    @Override
    public User toEntity(UserLoginMapper dto) {
        return modelMapper.map(dto,User.class);
    }
}
