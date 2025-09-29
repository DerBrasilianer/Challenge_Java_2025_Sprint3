package com.fiap.gestaofrota.service;

import com.fiap.gestaofrota.dto.UserDTO;
import com.fiap.gestaofrota.entity.UserEntity;
import com.fiap.gestaofrota.enums.Role;
import com.fiap.gestaofrota.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {

        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;

    }

    public boolean existsByUsername(String username) {

        return userRepository.findByUsername(username).isPresent();

    }

    public UserEntity registerNewUser(UserDTO userDTO) {
        Role role = (userDTO.getRole() != null) ? userDTO.getRole() : Role.USER;

        UserEntity user = UserEntity.builder()
                .username(userDTO.getUsername())
                .password(passwordEncoder.encode(userDTO.getPassword()))
                .role(role)
                .build();

        return userRepository.save(user);
    }

}