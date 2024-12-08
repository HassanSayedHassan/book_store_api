package com.example.our_book_store.responces;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class PaginatedApiResponse<T> {
    private String message;
    private List<T> data;
    private long totalRecords;
    private int totalPages;
}
