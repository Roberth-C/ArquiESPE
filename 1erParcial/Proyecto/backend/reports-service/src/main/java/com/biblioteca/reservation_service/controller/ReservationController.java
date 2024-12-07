package com.biblioteca.reservation_service.controller;

import com.biblioteca.reservation_service.dto.ReservationDto;
import com.biblioteca.reservation_service.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/reports")
public class ReportController {

    @Autowired
    private ReportService reportService;

    // Obtener todos los reportes por tipo
    @GetMapping
    public List<ReportDTO> getReportsByType(@RequestParam String type) {
        return reportService.getReportsByType(type).stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    // Crear un reporte
    @PostMapping
    public ReportDTO createReport(@RequestBody Report report) {
        return convertToDTO(reportService.createReport(report));
    }

    // Convertir entidad a DTO
    private ReportDTO convertToDTO(Report report) {
        ReportDTO dto = new ReportDTO();
        dto.setId(report.getId());
        dto.setReportType(report.getReportType());
        dto.setGeneratedDate(report.getGeneratedDate());
        dto.setGeneratedBy(report.getGeneratedBy());
        dto.setFilePath(report.getFilePath());
        return dto;
    }
}