package com.example.our_book_store.Controllers;


import com.example.our_book_store.Services.cart.ICartService;
import com.example.our_book_store.Services.order.IOrderService;
import com.example.our_book_store.responces.ApiResponse;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/order")
@AllArgsConstructor
public class OrderController {
    private final IOrderService orderService;

    @PostMapping("/create/{userId}")
    public ResponseEntity<ApiResponse> createOrder(@PathVariable String userId, @Valid @RequestParam Long bookId) {
        orderService.createOrder(userId);
        return ResponseEntity.ok().body(new ApiResponse("Order created successfully", null));
    }

    @GetMapping("/all/{userId}")
    public ResponseEntity<ApiResponse> getAllOrders(@PathVariable String userId) {
        var response = orderService.getAllOrders(userId);
        return ResponseEntity.ok().body(new ApiResponse("Orders fetched successfully", response));
    }


}
