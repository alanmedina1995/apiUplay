package com.example.apiuplay.controllers;

import com.example.apiuplay.models.UserDTO;
import com.example.apiuplay.services.UserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public UserDTO registerUser(@RequestBody UserDTO userDTO) {
        return userService.saveUser(userDTO);
    }

    @PostMapping("/login")
    public boolean login(@RequestBody UserDTO userDTO) {
        UserDTO usuarioExistente = userService.findByUsername(userDTO.getUsername());
        if (usuarioExistente != null && usuarioExistente.getPassword().equals(userDTO.getPassword())) {
            return true;
        }
        return false;
    }

}

