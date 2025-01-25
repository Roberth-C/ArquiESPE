package com.biblioteca.book_service.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "books")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String isbn;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String author;

    @Column
    private String category;

    @Column(nullable = false)
    private Integer quantity; // Cantidad total en inventario

    @Column(nullable = false)
    private Integer availableQuantity; // Cantidad disponible para reservas

    @Column
    private String location; // Ubicación en la biblioteca (opcional)

    @Column(nullable = false)
    private Integer year; // Año de publicación

    @Column(nullable = false)
    private Long librarianId; // ID del bibliotecario que registró el libro
}
