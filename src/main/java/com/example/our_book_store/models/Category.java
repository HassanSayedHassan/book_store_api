package com.example.our_book_store.models;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.ArrayList;
import java.util.List;

@Builder
@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@SequenceGenerator(name = "category_seq", sequenceName = "category_sequence", allocationSize = 5)
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "category_seq")
    private Long id;

    private String categoryName;


    @ManyToMany(fetch = FetchType.EAGER, mappedBy = "categories")
    @JsonIgnore
    List<Book> books = new ArrayList<>();


}
