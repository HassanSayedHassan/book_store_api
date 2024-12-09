package com.example.our_book_store.responces;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@Builder
public class ErrorResponse {
    private String message;
    private String path;
    private int status;
    private LocalDateTime timestamp;
}
