package com.example.our_book_store.models;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "_order")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private double totalPrice;

    private LocalDate orderDate;

    private String status;

    private String paymentType;


    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

}
