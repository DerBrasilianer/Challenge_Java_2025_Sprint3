package com.fiap.gestaofrota.controller;

import com.fiap.gestaofrota.dto.UserDTO;
import com.fiap.gestaofrota.entity.UserEntity;
import com.fiap.gestaofrota.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/register")
public class RegisterRestController {

    private final UserService userService;

    public RegisterRestController(UserService userService) {

        this.userService = userService;

    }

    @PostMapping
    public ResponseEntity<UserEntity> register(@RequestBody UserDTO userDTO) {

        if (userService.existsByUsername(userDTO.getUsername())) {

            return ResponseEntity.badRequest().build();

        }

        UserEntity createdUser = userService.registerNewUser(userDTO);
        return ResponseEntity.ok(createdUser);

    }

}
