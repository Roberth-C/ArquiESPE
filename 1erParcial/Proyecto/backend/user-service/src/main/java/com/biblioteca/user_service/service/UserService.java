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

    // Crear un nuevo usuario
    public User createUser(User user) {
        return userRepository.save(user);
    }

    // Obtener un usuario por ID
    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }

    // Obtener todos los usuarios
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    // Obtener usuarios por tipo
    public List<User> getUsersByType(String userType) {
        return userRepository.findByUserType(userType);
    }

    // Suspender usuario
    public void suspendUser(Long id) {
        Optional<User> user = userRepository.findById(id);
        user.ifPresent(u -> {
            u.setStatus(false);
            userRepository.save(u);
        });
    }

    public void addLoanToUser(Long userId, Long loanId) {
        Optional<User> userOptional = userRepository.findById(userId);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            user.getLoanIds().add(loanId); // Agregar el ID del préstamo a la lista
            userRepository.save(user);
        } else {
            throw new IllegalArgumentException("Usuario no encontrado con el ID proporcionado.");
        }
    }
    
    // Reactivar usuario
    public void activateUser(Long id) {
        Optional<User> user = userRepository.findById(id);
        user.ifPresent(u -> {
            u.setStatus(true);
            userRepository.save(u);
        });
    }
}