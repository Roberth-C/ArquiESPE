package com.biblioteca.book_service.service;

import com.biblioteca.book_service.dto.BookDto;
import com.biblioteca.book_service.entity.Book;
import com.biblioteca.book_service.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    // Crear un nuevo libro
    public Book createBook(Book book) {
        return bookRepository.save(book);
    }

    // Obtener un libro por ID
    public Optional<Book> getBookById(Long id) {
        return bookRepository.findById(id);
    }

    // Listar todos los libros
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    // Buscar libros por título
    public List<Book> searchBooksByTitle(String title) {
        return bookRepository.findByTitleContaining(title);
    }

    // Filtrar libros por categoría
    public List<Book> filterBooksByCategory(String category) {
        return bookRepository.findByCategory(category);
    }

    // Actualizar la información de un libro
    public Book updateBook(Long id, Book bookDetails) {
        Optional<Book> book = bookRepository.findById(id);
        if (book.isPresent()) {
            Book updatedBook = book.get();
            updatedBook.setTitle(bookDetails.getTitle());
            updatedBook.setAuthor(bookDetails.getAuthor());
            updatedBook.setCategory(bookDetails.getCategory());
            updatedBook.setQuantity(bookDetails.getQuantity());
            updatedBook.setLocation(bookDetails.getLocation());
            updatedBook.setAvailable(bookDetails.getAvailable());
            return bookRepository.save(updatedBook);
        }
        return null;
    }

    // Eliminar un libro
    public void deleteBook(Long id) {
        bookRepository.deleteById(id);
    }
}
