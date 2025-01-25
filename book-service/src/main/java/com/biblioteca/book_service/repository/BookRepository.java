package com.biblioteca.book_service.repository;

import com.biblioteca.book_service.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    Optional<Book> findByIsbn(String isbn);

    List<Book> findByTitleContainingIgnoreCase(String title);

    List<Book> findByAuthorContainingIgnoreCase(String author);

    // Filtros dinámicos (categoría, estado, rango de años)
    @Query("SELECT b FROM Book b WHERE " +
            "(:category IS NULL OR b.category = :category) AND " +
            "(:status IS NULL OR (b.quantity > 0 AND :status = 'Disponible') OR (b.quantity = 0 AND :status = 'Prestado')) AND " +
            "(:yearFrom IS NULL OR b.year >= :yearFrom) AND " +
            "(:yearTo IS NULL OR b.year <= :yearTo)")
    List<Book> filterBooks(String category, String status, Integer yearFrom, Integer yearTo);

    // Añadir para obtener libros específicos de un bibliotecario
    List<Book> findByLibrarianId(Long librarianId);
}
