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

    public UserEntity registerNewUser(UserDTO dto) {

        if (existsByUsername(dto.getUsername())) {

            throw new IllegalArgumentException("Nome de usuário já existe");

        }

        UserEntity user = UserEntity.builder()
                .username(dto.getUsername())
                .password(passwordEncoder.encode(dto.getPassword()))
                .role(dto.getRole() != null ? dto.getRole() : Role.USER)
                .build();

        return userRepository.save(user);

    }

}