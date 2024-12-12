package com.biblioteca.loan_service.service;

import com.biblioteca.loan_service.dto.LoanDTO;
import com.biblioteca.loan_service.entity.Loan;
import com.biblioteca.loan_service.repository.LoanRepository;

import lombok.Builder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Builder
@Service
public class LoanService {

    @Autowired
    private LoanRepository loanRepository;

    // Registrar un préstamo
    public LoanDTO registerLoan(LoanDTO loanDTO) {
        Loan loan = Loan.builder()
                .bookId(loanDTO.getBookId())
                .userId(loanDTO.getUserId())
                .loanDate(LocalDate.now())
                .dueDate(loanDTO.getDueDate())
                .status("ACTIVE")
                .build();
        Loan savedLoan = loanRepository.save(loan);
        return convertToDTO(savedLoan);
    }

    // Registrar devolución
    public LoanDTO returnBook(Long loanId) {
        Loan loan = loanRepository.findById(loanId)
                .orElseThrow(() -> new IllegalArgumentException("Préstamo no encontrado con ID: " + loanId));
        loan.setStatus("RETURNED");
        Loan updatedLoan = loanRepository.save(loan);
        return convertToDTO(updatedLoan);
    }

    // Renovar préstamo
    public LoanDTO renewLoan(Long loanId, LocalDate newDueDate) {
        Loan loan = loanRepository.findById(loanId)
                .orElseThrow(() -> new IllegalArgumentException("Préstamo no encontrado con ID: " + loanId));
        loan.setDueDate(newDueDate);
        Loan updatedLoan = loanRepository.save(loan);
        return convertToDTO(updatedLoan);
    }

    // Obtener todos los préstamos
    public List<LoanDTO> getAllLoans() {
        return loanRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    // Obtener préstamos activos
    public List<LoanDTO> getActiveLoans() {
        return loanRepository.findByStatus("ACTIVE").stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    // Historial de préstamos por usuario
    public List<LoanDTO> getLoanHistoryByUser(Long userId) {
        return loanRepository.findByUserId(userId).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    // Historial de préstamos por libro
    public List<LoanDTO> getLoanHistoryByBook(Long bookId) {
        return loanRepository.findByBookId(bookId).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    private LoanDTO convertToDTO(Loan loan) {
        return LoanDTO.builder()
                .id(loan.getId())
                .bookId(loan.getBookId())
                .userId(loan.getUserId())
                .loanDate(loan.getLoanDate())
                .dueDate(loan.getDueDate())
                .status(loan.getStatus())
                .build();
    }
}
