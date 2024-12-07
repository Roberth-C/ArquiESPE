package com.biblioteca.loan_service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
public class LoanDto {
    private Long id;
    private Long userId; // ID del usuario que realizó el préstamo
    private Long bookId; // ID del libro prestado
    private String bookTitle; // Título del libro prestado
    private LocalDate loanDate;
    private LocalDate dueDate;
    private LocalDate returnDate;
    private Boolean returned; // true = devuelto, false = pendiente
    private Double fine; // Multa por retraso, si aplica
}
