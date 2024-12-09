package com.example.our_book_store.Services.book;
import java.util.List;
import com.example.our_book_store.models.Category;
import com.example.our_book_store.models.Review;
import com.example.our_book_store.models.dto.BookRequestDto;
import com.example.our_book_store.models.dto.BookResponseDto;
import com.example.our_book_store.models.dto.ReviewRequestDto;
import com.example.our_book_store.responces.PaginatedApiResponse;

public interface IBookService {
    BookResponseDto addBook(BookRequestDto book);
    BookResponseDto updateBook(Long id,BookRequestDto book);
    void deleteBook(Long id);
    PaginatedApiResponse<BookResponseDto> getBook(int limit, int offset);

    // ===================== Reviews ======================
    void addReview(Long bookId, ReviewRequestDto review);
    void updateReview(Long bookId,Long reviewId, ReviewRequestDto review);
    void deleteReview(Long bookId, Long reviewId);

    List<Review> getBookReviews(Long id);

    // ===================== Categories ======================
    void addCategory(Long bookId, Long categoryId);
    void removeCategory(Long bookId, Long categoryId);
    List<Category> getBookCategory(Long id);
}
