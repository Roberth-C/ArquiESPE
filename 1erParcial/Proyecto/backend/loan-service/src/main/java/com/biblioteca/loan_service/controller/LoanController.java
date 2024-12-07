package com.biblioteca.loan_service.controller;

import com.biblioteca.loan_service.dto.LoanDto;
import com.biblioteca.loan_service.service.LoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/loans")
public class LoanController {

    @Autowired
    private LoanService loanService;

    // Obtener todos los préstamos activos
    @GetMapping("/active")
    public List<LoanDTO> getActiveLoans() {
        return loanService.getActiveLoans().stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    // Obtener el historial de préstamos por usuario
    @GetMapping("/user/{userId}")
    public List<LoanDTO> getLoanHistoryByUserId(@PathVariable Long userId) {
        return loanService.getLoanHistoryByUserId(userId).stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    // Renovar un préstamo
    @PutMapping("/{id}/renew")
    public LoanDTO renewLoan(@PathVariable Long id) {
        Loan renewedLoan = loanService.renewLoan(id);
        return convertToDTO(renewedLoan);
    }

    // Registrar un préstamo
    @PostMapping
    public LoanDTO createLoan(@RequestBody Loan loan) {
        return convertToDTO(loanService.createLoan(loan));
    }

    // Registrar una devolución
    @PutMapping("/{id}/return")
    public LoanDTO returnLoan(@PathVariable Long id) {
        return convertToDTO(loanService.returnLoan(id));
    }

    // Reservar un libro
    @PostMapping("/reserve")
    public LoanDTO reserveBook(@RequestParam Long userId, @RequestParam Long bookId) {
        Loan reservation = loanService.reserveBook(userId, bookId);
        return convertToDTO(reservation);
    }


    // Convertir entidad a DTO
    private LoanDTO convertToDTO(Loan loan) {
        LoanDTO dto = new LoanDTO();
        dto.setId(loan.getId());
        dto.setUserId(loan.getUser().getId());
        dto.setBookId(loan.getBook().getId());
        dto.setBookTitle(loan.getBook().getTitle());
        dto.setLoanDate(loan.getLoanDate());
        dto.setDueDate(loan.getDueDate());
        dto.setReturnDate(loan.getReturnDate());
        dto.setReturned(loan.getReturned());
        dto.setFine(loan.getFine());
        return dto;
    }
}
