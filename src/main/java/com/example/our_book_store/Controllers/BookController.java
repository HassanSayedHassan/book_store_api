package com.example.our_book_store.Controllers;


import com.example.our_book_store.Services.book.IBookService;
import com.example.our_book_store.models.Category;
import com.example.our_book_store.models.dto.BookRequestDto;
import com.example.our_book_store.models.dto.BookResponseDto;
import com.example.our_book_store.models.dto.ReviewRequestDto;
import com.example.our_book_store.responces.ApiResponse;
import com.example.our_book_store.responces.PaginatedApiResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/books")
@AllArgsConstructor
public class BookController {
    private final IBookService bookService;

    @PostMapping()
    public ResponseEntity<ApiResponse> addBook(@RequestBody BookRequestDto bookRequestDto) {
        BookResponseDto book=bookService.addBook(bookRequestDto);
        return new ResponseEntity<>(new ApiResponse("Book added successfully",book), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse> updateBook(@RequestBody BookRequestDto bookRequestDto, @PathVariable Long id) {
        BookResponseDto book=bookService.updateBook(id,bookRequestDto);
        return new ResponseEntity<>(new ApiResponse("Book updated successfully",book), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteBook(@PathVariable Long id) {
        bookService.deleteBook(id);
        return new ResponseEntity<>(new ApiResponse("Book deleted successfully",null), HttpStatus.OK);
    }

    @GetMapping()
    public ResponseEntity<PaginatedApiResponse<BookResponseDto>> getBook(@RequestParam(defaultValue = "10") int limit,
                                                                         @RequestParam(defaultValue = "0") int offset) {
        var response = bookService.getBook(limit, offset);
        return ResponseEntity.ok().body(response);
    }

    /// ================================  Reviews ===================================
    @PostMapping("/review/{id}")
    public ResponseEntity<ApiResponse> addReview(@RequestBody ReviewRequestDto review, @PathVariable Long id) {
     bookService.addReview(id,review);
     return new ResponseEntity<>(new ApiResponse("Review added successfully",null), HttpStatus.CREATED);
    }
    @PutMapping("/review/{id}")
    public ResponseEntity<ApiResponse> updateReview(@RequestParam Long reviewId,@RequestBody ReviewRequestDto review, @PathVariable Long id) {
     bookService.updateReview(id,reviewId,review);
     return new ResponseEntity<>(new ApiResponse("Review updated successfully",null), HttpStatus.CREATED);
    }
    @DeleteMapping("/review/{id}")
    public ResponseEntity<ApiResponse> deleteReview(@RequestParam Long reviewId, @PathVariable Long id) {
        bookService.deleteReview(id,reviewId);
        return new ResponseEntity<>(new ApiResponse("Review Deleted successfully",null), HttpStatus.OK);
    }

    @GetMapping("/reviews/{id}")
    public ResponseEntity<ApiResponse> getBookReviews(@PathVariable Long id) {
        var response = bookService.getBookReviews(id);
        return new ResponseEntity<>(new ApiResponse("Reviews fetched successfully",response), HttpStatus.OK);
    }

    /// ================================  Category ===================================
    @PostMapping("/category/{id}")
    public ResponseEntity<ApiResponse> addCategory(@RequestParam Long categoryId, @PathVariable Long id) {
        bookService.addCategory(id,categoryId);
        return new ResponseEntity<>(new ApiResponse("Category added successfully",null), HttpStatus.CREATED);
    }
    @DeleteMapping("/category/{id}")
    public ResponseEntity<ApiResponse> removeCategory(@RequestParam Long categoryId, @PathVariable Long id) {
        bookService.removeCategory(id,categoryId);
        return new ResponseEntity<>(new ApiResponse("Category Deleted successfully",null), HttpStatus.OK);
    }

    @GetMapping("/category/{id}")
    public ResponseEntity<ApiResponse> getBookCategory(@PathVariable Long id) {
        var response = bookService.getBookCategory(id);
        return new ResponseEntity<>(new ApiResponse("Category fetched successfully",response), HttpStatus.OK);
    }
}
