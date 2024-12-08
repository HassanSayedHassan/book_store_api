package com.example.our_book_store.mappers;

import com.example.our_book_store.models.User;
import com.example.our_book_store.models.dto.UserSignUpDto;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class UserSignupMapper implements Mapper<UserSignUpDto, User>{
    private final ModelMapper modelMapper;

    @Override
    public UserSignUpDto toDto(User entity) {
        return modelMapper.map(entity,UserSignUpDto.class);
    }

    @Override
    public User toEntity(UserSignUpDto dto) {
        return modelMapper.map(dto,User.class);
    }
}
