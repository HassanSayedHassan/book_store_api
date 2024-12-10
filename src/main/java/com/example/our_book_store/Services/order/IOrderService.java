package com.example.our_book_store.Services.order;

import com.example.our_book_store.models.Order;
import java.util.List;

public interface IOrderService {

    void createOrder(String userId);
    List<Order> getAllOrders(String userId);

}
