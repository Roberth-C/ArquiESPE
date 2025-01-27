package com.biblioteca.user_service.entity;

import jakarta.persistence.*;

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

    // Constructor sin parámetros
    public User() {
    }

    // Constructor con parámetros
    public User(Long id, String idInstitucional, String username, String password, Integer role, Boolean status) {
        this.id = id;
        this.idInstitucional = idInstitucional;
        this.username = username;
        this.password = password;
        this.role = role;
        this.status = status;
    }

    // Getters y Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIdInstitucional() {
        return idInstitucional;
    }

    public void setIdInstitucional(String idInstitucional) {
        this.idInstitucional = idInstitucional;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getRole() {
        return role;
    }

    public void setRole(Integer role) {
        this.role = role;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }
}
