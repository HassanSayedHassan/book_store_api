package com.example.our_book_store.Services.order;

import com.example.our_book_store.exceptions.ResourceNotFoundException;
import com.example.our_book_store.models.CartItem;
import com.example.our_book_store.models.Order;
import com.example.our_book_store.models.OrderItem;
import com.example.our_book_store.models.User;
import com.example.our_book_store.repository.OrderRepository;
import com.example.our_book_store.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Service
@AllArgsConstructor
public class OrderServiceImpl implements IOrderService {
    private final UserRepository userRepository;
    private final OrderRepository orderRepository;

    @Override
    public void createOrder(String userId) {
        User user= userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User not found with id " + userId));

        Order order = new Order();
        order.setUser(user);
        order.setStatus("Pending");
        order.setPaymentType("cash");
        order.setUserEmail(user.getEmail());
        order.setOrderDate(LocalDate.now());
        order.setTotalItems(user.getCart().getTotalItems());
        order.setTotalPrice(user.getCart().getTotalPrice());

        //set orderItems
        for(CartItem cartItem:user.getCart().getCartItems()){
            order.getOrderItems().add(
                    OrderItem.builder()
                            .book(cartItem.getBook())
                            .quantity(cartItem.getQuantity())
                            .unitPrice(cartItem.getUnitPrice())
                            .totalPrice(cartItem.getTotalPrice())
                            .order(order) // Set the reference to the order
                            .build());
        }

        user.getOrders().add(order);

        // reset cart
        user.getCart().getCartItems().clear();
        user.getCart().setTotalItems(0);
        user.getCart().setTotalPrice(BigDecimal.ZERO);


        userRepository.save(user);
    }



    @Override
    public List<Order> getAllOrders(String userId) {
        return userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User not found with id " + userId)).getOrders();
    }
}
