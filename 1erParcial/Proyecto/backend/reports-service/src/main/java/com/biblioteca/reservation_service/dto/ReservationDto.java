package com.biblioteca.reservation_service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
public class ReservationDto {
    private Long id;
    private String reportType; // Tipo de reporte: inventario, préstamos activos, historial de usuarios
    private LocalDate generatedDate;
    private String generatedBy; // Usuario que generó el reporte
    private String filePath; // Ubicación del archivo generado
}
