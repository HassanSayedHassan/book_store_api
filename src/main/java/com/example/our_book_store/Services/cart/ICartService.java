package com.example.our_book_store.Services.cart;

public interface ICartService {

    void addBookToCart(String userId,Long bookId);

    void removeBookFromCart(String userId,Long bookId);

    void clearCart(String userId);

    void updateQuantity(String userId,Long bookId,int quantity);

}
