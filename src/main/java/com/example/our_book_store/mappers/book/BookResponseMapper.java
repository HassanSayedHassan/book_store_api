package com.example.our_book_store.mappers.book;


import com.example.our_book_store.mappers.Mapper;
import com.example.our_book_store.models.Book;
import com.example.our_book_store.models.dto.BookResponseDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class BookResponseMapper implements Mapper<BookResponseDto,Book> {
    private final ModelMapper modelMapper;

    @Override
    public BookResponseDto toDto(Book entity) {
        return modelMapper.map(entity,BookResponseDto.class);
    }

    @Override
    public Book toEntity(BookResponseDto dto) {
        return modelMapper.map(dto,Book.class);
    }
}
