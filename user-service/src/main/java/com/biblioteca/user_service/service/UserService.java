package com.biblioteca.user_service.service;

import com.biblioteca.user_service.entity.User;
import com.biblioteca.user_service.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    // Registrar un usuario
    public User createUser(User user) {
        return userRepository.save(user);
    }

    public boolean authenticateUser(String username, String password) {
        return userRepository.findByUsername(username)
                .map(user -> user.getPassword().equals(password)) // Valida la contraseña
                .orElse(false); // Devuelve falso si el usuario no existe
    }

    // Obtener un usuario por ID institucional
    public Optional<User> getUserByIdInstitucional(String idInstitucional) {
        return userRepository.findByIdInstitucional(idInstitucional);
    }

    // Obtener todos los usuarios
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    // Obtener usuarios por rol
    public List<User> getUsersByRole(Integer role) {
        return userRepository.findByRole(role);
    }

    // Suspender un usuario por ID institucional
    public void suspendUserByIdInstitucional(String idInstitucional) {
        User user = userRepository.findByIdInstitucional(idInstitucional)
                .orElseThrow(() -> new IllegalArgumentException("Usuario no encontrado con ID institucional: " + idInstitucional));
        user.setStatus(false); // Suspender usuario
        userRepository.save(user);
    }


    public User updateUserByIdInstitucional(String idInstitucional, User updatedUser) {
        User existingUser = userRepository.findByIdInstitucional(idInstitucional)
                .orElseThrow(() -> new IllegalArgumentException("Usuario no encontrado"));
        existingUser.setUsername(updatedUser.getUsername());
        existingUser.setRole(updatedUser.getRole());
        userRepository.save(existingUser);
        return existingUser;
    }
    

    // Activar un usuario por ID institucional
    public void activateUserByIdInstitucional(String idInstitucional) {
        User user = userRepository.findByIdInstitucional(idInstitucional)
                .orElseThrow(() -> new IllegalArgumentException("Usuario no encontrado con ID institucional: " + idInstitucional));
        user.setStatus(true); // Activar usuario
        userRepository.save(user);
    }
}