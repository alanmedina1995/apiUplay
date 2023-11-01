package com.example.apiuplay.controllers;

import com.example.apiuplay.models.User;
import com.example.apiuplay.models.UserDTO;
import com.example.apiuplay.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<UserDTO> registerUser(@RequestBody UserDTO userDTO) {
        User savedUser = userService.saveUser(userDTO); // Guarda el usuario en el repositorio
        UserDTO userResponse = userService.convertToUserDTO(savedUser);
        return new ResponseEntity<>(userResponse, HttpStatus.CREATED);

    }

    @PostMapping("/login")
    public ResponseEntity<Boolean> login(@RequestBody UserDTO userDTO) {
        User findUser = userService.findByUsername(userDTO.getUsername());

        if (findUser != null && findUser.getPassword().equals(userDTO.getPassword())) {
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}

