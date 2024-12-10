package com.example.our_book_store.models;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "_order")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String userEmail;
    private BigDecimal totalPrice;
    private double totalItems;

    private LocalDate orderDate;
    private String status;
    private String paymentType;

    @OneToMany(mappedBy = "order",cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderItem> orderItems =new ArrayList<>();


    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private User user;

}
