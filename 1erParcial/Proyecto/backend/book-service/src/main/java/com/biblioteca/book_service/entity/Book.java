package com.biblioteca.book_service.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "books")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String author;

    @Column(nullable = false, unique = true)
    private String isbn;

    @Column(nullable = false)
    private String category;

    @Column(nullable = false)
    private Boolean available;

    @Column(nullable = false)
    private String location; // Ubicación en la biblioteca

    @Column(nullable = false)
    private Integer quantity; // Cantidad en inventario

    @OneToMany(mappedBy = "book", cascade = CascadeType.ALL)
    private List<Loan> loans; // Historial de préstamos relacionados
}
