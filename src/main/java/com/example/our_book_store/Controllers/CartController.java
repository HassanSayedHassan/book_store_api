package com.example.our_book_store.Controllers;


import com.example.our_book_store.Services.cart.ICartService;
import com.example.our_book_store.responces.ApiResponse;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.websocket.server.PathParam;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/cart")
@AllArgsConstructor
public class CartController {
    private final ICartService cartService;

    @PostMapping("/add/{userId}")
    public ResponseEntity<ApiResponse> addBookToCart(@PathVariable String userId, @RequestParam Long bookId) {
        cartService.addBookToCart(userId,bookId);
        return ResponseEntity.ok().body(new ApiResponse("Item added successfully", null));
    }

    @PostMapping("/remove/{userId}")
    public ResponseEntity<ApiResponse> removeBookFromCart(@PathVariable String userId, @RequestParam Long bookId) {
        cartService.removeBookFromCart(userId,bookId);
        return ResponseEntity.ok().body(new ApiResponse("Item removed successfully", null));
    }

    @PutMapping("/updateQuantity/{userId}")
    public ResponseEntity<ApiResponse> updateQuantity(@PathVariable String userId, @RequestParam Long bookId,@Valid @Min(0) @RequestParam int quantity) {
        cartService.updateQuantity(userId, bookId, quantity);
        return ResponseEntity.ok().body(new ApiResponse("Quantity updated successfully", null));
    }

}
