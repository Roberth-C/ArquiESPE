package com.biblioteca.loan_service.repository;

import com.biblioteca.loan_service.entity.Loan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReportRepository extends JpaRepository<Report, Long> {

    // Buscar reportes por tipo (inventario, préstamos, etc.)
    List<Report> findByReportType(String reportType);

    // Buscar reportes generados por un usuario específico
    List<Report> findByGeneratedBy(String generatedBy);

    // Buscar reportes generados dentro de un rango de fechas
    List<Report> findByGeneratedDateBetween(LocalDate startDate, LocalDate endDate);
}
