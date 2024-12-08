package com.example.our_book_store.mappers;

public interface Mapper<D,E> {
    D toDto(E entity);
    E toEntity(D dto);
}
