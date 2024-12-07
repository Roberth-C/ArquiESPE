package com.biblioteca.loan_service.service;

import com.biblioteca.loan_service.dto.LoanDto;
import com.biblioteca.loan_service.entity.Loan;
import com.biblioteca.loan_service.repository.LoanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class LoanService {

    @Autowired
    private LoanRepository loanRepository;

    // Registrar un préstamo
    public Loan createLoan(Loan loan) {
        loan.setLoanDate(LocalDate.now());
        loan.setDueDate(LocalDate.now().plusDays(14)); // Ejemplo: 2 semanas de préstamo
        loan.setReturned(false);
        return loanRepository.save(loan);
    }

    // Renovar un préstamo
    public Loan renewLoan(Long loanId) {
        Optional<Loan> loanOptional = loanRepository.findById(loanId);
        if (loanOptional.isPresent()) {
            Loan loan = loanOptional.get();
            if (loan.getReturned()) {
                throw new IllegalStateException("El préstamo ya ha sido devuelto y no se puede renovar.");
            }
            loan.setDueDate(loan.getDueDate().plusWeeks(2)); // Extender 2 semanas más
            return loanRepository.save(loan);
        }
        throw new IllegalArgumentException("Préstamo no encontrado con el ID proporcionado.");
    }

    // Registrar una devolución
    public Loan returnLoan(Long id) {
        Optional<Loan> loan = loanRepository.findById(id);
        if (loan.isPresent()) {
            Loan updatedLoan = loan.get();
            updatedLoan.setReturned(true);
            updatedLoan.setReturnDate(LocalDate.now());
            return loanRepository.save(updatedLoan);
        }
        return null;
    }

    // Reservar un libro
    public Loan reserveBook(Long userId, Long bookId) {
        Loan reservation = new Loan();
        reservation.setUserId(userId);
        reservation.setBookId(bookId);
        reservation.setLoanDate(LocalDate.now());
        reservation.setDueDate(LocalDate.now().plusDays(7)); // La reserva es válida por 7 días
        reservation.setReturned(false);
        return loanRepository.save(reservation);
    }

    // Obtener préstamos activos
    public List<Loan> getActiveLoans() {
        return loanRepository.findByReturnedFalse();
    }

    // Obtener el historial de préstamos de un usuario
    public List<Loan> getLoanHistoryByUserId(Long userId) {
        return loanRepository.findByUserIdAndReturnedTrue(userId);
    }
}