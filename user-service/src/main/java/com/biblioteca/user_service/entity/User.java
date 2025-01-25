package com.biblioteca.user_service.entity;
import jakarta.persistence.*;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, unique = true)
    private String idInstitucional; // ID de la universidad
    @Column(nullable = false, unique = true)
    private String username;
    @Column(nullable = false)
    private String password;
    @Column(nullable = false)
    private Integer role; // 1: Admin, 2: Bibliotecario, 3: Estudiante
    @Column(nullable = false)
    private Boolean status; // true = habilitado, false = suspendido
}
