package com.example.our_book_store.Services.cart;

import com.example.our_book_store.exceptions.ResourceNotFoundException;
import com.example.our_book_store.models.Book;
import com.example.our_book_store.models.Cart;
import com.example.our_book_store.models.CartItem;
import com.example.our_book_store.models.User;
import com.example.our_book_store.repository.BookRepository;
import com.example.our_book_store.repository.CartRepository;
import com.example.our_book_store.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@AllArgsConstructor
public class CartServiceImpl implements ICartService {
    private final UserRepository userRepository;
    private final BookRepository bookRepository;
    private final CartRepository cartRepository;


    private BigDecimal getTotalPrice(Cart cart) {
        BigDecimal totalPrice = BigDecimal.ZERO; // Use BigDecimal.ZERO for clarity
        for (CartItem cartItem : cart.getCartItems()) {
            totalPrice = totalPrice.add(cartItem.getTotalPrice());
        }
        return totalPrice;
    }


    @Override
    public void addBookToCart(String userId, Long bookId) {
        User user= userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User not found with id " + userId));
        Book book= bookRepository.findById(bookId).orElseThrow(() -> new ResourceNotFoundException("Book not found with id " + bookId));
        //check if null or not
        Cart cart=user.getCart();
        if(cart==null){
            cart=new Cart();
            cart.setUser(user);
            user.setCart(cart);
        }

        /// add book already exist
        for (CartItem cartItem : cart.getCartItems()) {
            if (cartItem.getBook().getId().equals(bookId)) {
                cartItem.setQuantity(cartItem.getQuantity().add(BigDecimal.ONE));
                cartItem.setTotalPrice(cartItem.getTotalPrice().add(BigDecimal.valueOf(book.getPrice())));
                cart.setTotalItems(cart.getCartItems().size());
                cart.setTotalPrice(getTotalPrice(cart));
                user.setCart(cart);
                userRepository.save(user);
                return;
            }
        }


        CartItem cartItem=new CartItem();
        cartItem.setBook(book);
        cartItem.setQuantity(BigDecimal.valueOf(1));
        cartItem.setUnitPrice(BigDecimal.valueOf(book.getPrice()));
        cartItem.setTotalPrice(BigDecimal.valueOf(book.getPrice()));
        cartItem.setCart(cart);
        cart.getCartItems().add(cartItem);
        cart.setUserEmail(user.getEmail());
        cart.setTotalPrice(getTotalPrice(cart));
        cart.setTotalItems(cart.getCartItems().size());

        user.setCart(cart);
        userRepository.save(user);
    }

    @Override
    public void removeBookFromCart(String userId, Long bookId) {
        User user= userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User not found with id " + userId));
        Cart cart=user.getCart();
        cart.getCartItems().removeIf(cartItem -> cartItem.getBook().getId().equals(bookId));
        cart.setTotalPrice(getTotalPrice(cart));
        cart.setTotalItems(cart.getCartItems().size());

        user.setCart(cart);
        userRepository.save(user);
    }

    @Override
    public void clearCart(String userId) {
        User user= userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User not found with id " + userId));
        Cart cart=user.getCart();
        cart.getCartItems().clear();
        cart.setTotalPrice(getTotalPrice(cart));
        cart.setTotalItems(cart.getCartItems().size());

        user.setCart(cart);
        userRepository.save(user);

    }

    @Override
    public void updateQuantity(String userId, Long bookId, int quantity) {
        User user= userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User not found with id " + userId));
        Book book= bookRepository.findById(bookId).orElseThrow(() -> new ResourceNotFoundException("Book not found with id " + bookId));
        Cart cart=user.getCart();
        for (CartItem cartItem : cart.getCartItems()) {
            if (cartItem.getBook().getId().equals(bookId)) {
                cartItem.setQuantity(BigDecimal.valueOf(quantity));
                cartItem.setTotalPrice(BigDecimal.valueOf(quantity * book.getPrice()));
                cart.setTotalPrice(getTotalPrice(cart));
                cart.setTotalItems(cart.getCartItems().size());

                user.setCart(cart);
                userRepository.save(user);
                return; // Exit the method after saving the user
            }
        }
        throw new ResourceNotFoundException("Book not found  with id in your cart " + bookId);

    }
}
