package com.example.our_book_store.mappers.review;


import com.example.our_book_store.mappers.Mapper;
import com.example.our_book_store.models.Book;
import com.example.our_book_store.models.Review;
import com.example.our_book_store.models.dto.ReviewRequestDto;
import com.example.our_book_store.models.dto.ReviewRequestDto;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class ReviewRequestMapper implements Mapper<ReviewRequestDto, Review> {
    private final ModelMapper modelMapper;
    @Override
    public ReviewRequestDto toDto(Review entity) {
        return modelMapper.map(entity,ReviewRequestDto.class);
    }

    @Override
    public Review toEntity(ReviewRequestDto dto) {
        return modelMapper.map(dto,Review.class);
    }
}
