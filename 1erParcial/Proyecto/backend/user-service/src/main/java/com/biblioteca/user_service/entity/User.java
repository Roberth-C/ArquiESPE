package com.biblioteca.user_service.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String email;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private UserType userType; // Ejemplo: ADMIN, STUDENT, TEACHER

    @Column(nullable = false)
    private Boolean status; // true = activo, false = suspendido

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Loan> loans; // Historial de préstamos
}
