package com.biblioteca.user_service.repository;

import com.biblioteca.user_service.entity.User;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    // Buscar un usuario por su nombre de usuario
    Optional<User> findByUsername(String username);

    // Buscar un usuario por su correo
    Optional<User> findByEmail(String email);

    // Buscar usuarios por tipo (ejemplo: ADMIN, STUDENT)
    List<User> findByUserType(String userType);
}
