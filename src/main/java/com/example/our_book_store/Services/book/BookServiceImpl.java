package com.example.our_book_store.Services.book;

import com.example.our_book_store.exceptions.ResourceNotFoundException;
import com.example.our_book_store.mappers.book.BookRequestMapper;
import com.example.our_book_store.mappers.book.BookResponseMapper;
import com.example.our_book_store.mappers.review.ReviewRequestMapper;
import com.example.our_book_store.models.Book;
import com.example.our_book_store.models.Category;
import com.example.our_book_store.models.Review;
import com.example.our_book_store.models.dto.BookRequestDto;
import com.example.our_book_store.models.dto.BookResponseDto;
import com.example.our_book_store.models.dto.ReviewRequestDto;
import com.example.our_book_store.repository.BookRepository;
import com.example.our_book_store.repository.CategoryRepository;
import com.example.our_book_store.repository.ReviewRepository;
import com.example.our_book_store.responces.PaginatedApiResponse;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.List;



@AllArgsConstructor
@Service
public class BookServiceImpl implements IBookService {
    private final BookRepository bookRepository;
    private final ReviewRequestMapper reviewRequestMapper;
    private final BookRequestMapper bookRequestMapper;
    private final BookResponseMapper bookResponseMapper;
    private final ReviewRepository reviewRepository;
    private final CategoryRepository categoryRepository;

    @Override
    public BookResponseDto addBook(BookRequestDto book) {
        Book newBook = bookRequestMapper.toEntity(book);
        Book savedBook =bookRepository.save(newBook);
        return bookResponseMapper.toDto(savedBook);
    }

    @Override
    public BookResponseDto updateBook(Long id,BookRequestDto book) {
        //check if book exists
        Optional<Book> bookOptional = bookRepository.findById(id);
        if (bookOptional.isEmpty()) {
            throw new ResourceNotFoundException("Book not found with id " + id);
        }

        Book updatedBook = bookOptional.get();
        updatedBook.setAuther(book.getAuther());
//        updatedBook.setAvailable(book.getAvailable());
        updatedBook.setPhoto(book.getPhoto());
        updatedBook.setPopularity(book.getPopularity());
        updatedBook.setPrice(book.getPrice());
        updatedBook.setTitle(book.getTitle());

        Book savedBook = bookRepository.save(updatedBook);
        return bookResponseMapper.toDto(savedBook);
    }

    @Override
    public void deleteBook(Long id) {

        Optional<Book> bookOptional = bookRepository.findById(id);
        if (bookOptional.isEmpty()) {
            throw new ResourceNotFoundException("Book not found with id " + id);
        }
        bookRepository.deleteById(id);

    }

    @Override
    public PaginatedApiResponse<BookResponseDto> getBook(int limit, int offset) {
        Pageable pageable = PageRequest.of(offset / limit, limit);
        Page<Book> books = bookRepository.findAll(pageable);
        return new PaginatedApiResponse<>("Get Books Successfully", books.getContent().stream().map(bookResponseMapper::toDto).toList(), books.getTotalElements(), books.getTotalPages());
    }

    @Override
    public void addReview(Long bookId, ReviewRequestDto review) {
        // check if book exists
        Optional<Book> bookOptional = bookRepository.findById(bookId);
        if (bookOptional.isEmpty()) {
            throw new ResourceNotFoundException("Book not found with id " + bookId);
        }
        Book book = bookOptional.get();
        book.getReviews().add(reviewRequestMapper.toEntity(review));
        bookRepository.save(book);
    }

    @Override
    public void updateReview(Long bookId, Long reviewId, ReviewRequestDto review) {
        // check if book exist
        Optional<Book> optionalBook= bookRepository.findById(bookId);
        if (optionalBook.isEmpty()) {
            throw new ResourceNotFoundException("Book not found with id " + bookId);
        }
        Book book = optionalBook.get();
        Optional<Review> reviewOptional = book.getReviews().stream().filter(r -> r.getId().equals(reviewId)).findFirst();
        if (reviewOptional.isEmpty()) {
            throw new ResourceNotFoundException("Review not found with id " + reviewId);
        }
        Review updatedReview = reviewOptional.get();
        updatedReview.setComment(review.getComment());
        bookRepository.save(book);
    }

    @Transactional
    @Override
    public void deleteReview(Long bookId, Long reviewId) {
        // Fetch the book entity
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new ResourceNotFoundException("Book not found with id: " + bookId));

        // Fetch the review entity
        Review review = reviewRepository.findById(reviewId)
                .orElseThrow(() -> new ResourceNotFoundException("Review not found with id: " + reviewId));


        if (book.getReviews().contains(review)) {
            book.getReviews().remove(review);
            review.getBooks().remove(book); // Ensure bidirectional relationship is updated
        } else {
            throw new IllegalArgumentException("The specified review is not associated with this book.");
        }
        bookRepository.save(book);

        // This ensures the review is completely removed
        reviewRepository.delete(review);
    }

    @Override
    public List<Review> getBookReviews(Long id) {
        // check if book exist
        Optional<Book> bookOptional = bookRepository.findById(id);
        if (bookOptional.isEmpty()) {
            throw new ResourceNotFoundException("Book not found with id " + id);
        }

        Book book = bookOptional.get();
        return book.getReviews();
    }

    @Override
    public void addCategory(Long bookId, Long categoryId) {
        Book book = bookRepository.findById(bookId).orElseThrow(() -> new ResourceNotFoundException("Book not found with id " + bookId));
        Category category = categoryRepository.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("Category not found with id " + categoryId));
        book.getCategories().add(category);
        bookRepository.save(book);
    }


    @Override
    public void removeCategory(Long bookId, Long categoryId) {
        Book book = bookRepository.findById(bookId).orElseThrow(() -> new ResourceNotFoundException("Book not found with id " + bookId));
        Category category = categoryRepository.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("Category not found with id " + categoryId));

        book.getCategories().remove(category);
        bookRepository.save(book);
    }

    @Override
    public List<Category> getBookCategory(Long id) {
        // check if book exist
        Optional<Book> bookOptional = bookRepository.findById(id);
        if (bookOptional.isEmpty()) {
            throw new ResourceNotFoundException("Book not found with id " + id);
        }

        Book book = bookOptional.get();
        return book.getCategories();

    }
}
