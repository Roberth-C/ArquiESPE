package com.biblioteca.book_service.dto;

import com.biblioteca.book_service.entity.Book;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class BookDto {
    private Long id;
    private String title;
    private String author;
    private String isbn;
    private String category;
    private Boolean available;
    private String location; // Ubicación física en la biblioteca
    private Integer quantity; // Cantidad de ejemplares en inventario
}