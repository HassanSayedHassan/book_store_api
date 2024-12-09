package com.example.our_book_store.mappers.book;


import com.example.our_book_store.mappers.Mapper;
import com.example.our_book_store.models.Book;
import com.example.our_book_store.models.dto.BookRequestDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class BookRequestMapper implements Mapper<BookRequestDto, Book> {
    private final ModelMapper modelMapper;
    @Override
    public BookRequestDto toDto(Book entity) {
        return modelMapper.map(entity,BookRequestDto.class);
    }

    @Override
    public Book toEntity(BookRequestDto dto) {
        return modelMapper.map(dto,Book.class);
    }
}
