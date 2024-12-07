package com.biblioteca.book_service.controller;

import com.biblioteca.book_service.dto.BookDto;
import com.biblioteca.book_service.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/books")
public class BookController {

    @Autowired
    private BookService bookService;

    // Obtener todos los libros
    @GetMapping
    public List<BookDTO> getAllBooks() {
        return bookService.getAllBooks().stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    // Obtener libro por ID
    @GetMapping("/{id}")
    public Optional<BookDTO> getBookById(@PathVariable Long id) {
        return bookService.getBookById(id).map(this::convertToDTO);
    }

    // Crear un nuevo libro
    @PostMapping
    public BookDTO createBook(@RequestBody Book book) {
        return convertToDTO(bookService.createBook(book));
    }

    // Actualizar libro
    @PutMapping("/{id}")
    public BookDTO updateBook(@PathVariable Long id, @RequestBody Book bookDetails) {
        return convertToDTO(bookService.updateBook(id, bookDetails));
    }

    // Eliminar libro
    @DeleteMapping("/{id}")
    public void deleteBook(@PathVariable Long id) {
        bookService.deleteBook(id);
    }

    // Convertir entidad a DTO
    private BookDTO convertToDTO(Book book) {
        BookDTO dto = new BookDTO();
        dto.setId(book.getId());
        dto.setTitle(book.getTitle());
        dto.setAuthor(book.getAuthor());
        dto.setIsbn(book.getIsbn());
        dto.setCategory(book.getCategory());
        dto.setAvailable(book.getAvailable());
        dto.setLocation(book.getLocation());
        dto.setQuantity(book.getQuantity());
        return dto;
    }
}