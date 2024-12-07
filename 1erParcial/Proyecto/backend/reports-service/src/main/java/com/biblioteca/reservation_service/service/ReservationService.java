package com.biblioteca.reservation_service.service;

import com.biblioteca.reservation_service.dto.ReservationDto;
import com.biblioteca.reservation_service.entity.Reservation;
import com.biblioteca.reservation_service.repository.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReportService {

    @Autowired
    private ReportRepository reportRepository;

    // Crear un nuevo reporte
    public Report createReport(Report report) {
        report.setGeneratedDate(LocalDate.now());
        return reportRepository.save(report);
    }

    // Obtener reportes por tipo
    public List<Report> getReportsByType(String reportType) {
        return reportRepository.findByReportType(reportType);
    }

    // Obtener reportes generados por un usuario
    public List<Report> getReportsByGeneratedBy(String generatedBy) {
        return reportRepository.findByGeneratedBy(generatedBy);
    }

    // Obtener reportes en un rango de fechas
    public List<Report> getReportsBetweenDates(LocalDate startDate, LocalDate endDate) {
        return reportRepository.findByGeneratedDateBetween(startDate, endDate);
    }
}
