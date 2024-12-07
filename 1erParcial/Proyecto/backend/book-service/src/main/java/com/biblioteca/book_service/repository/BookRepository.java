package com.biblioteca.book_service.repository;

import com.biblioteca.book_service.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    List<Book> findByCategory(String category);

    List<Book> findByTitleContaining(String title);

    List<Book> findByAuthorContaining(String author);

    // Libros disponibles
    List<Book> findByAvailableTrue();

    // Filtrar por rango de publicación (requiere campo adicional de fecha)
    List<Book> findByPublicationDateBetween(LocalDate startDate, LocalDate endDate);
}