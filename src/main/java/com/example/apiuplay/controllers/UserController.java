package com.example.apiuplay.controllers;

import com.example.apiuplay.models.User;
import com.example.apiuplay.models.UserDTO;
import com.example.apiuplay.services.UserService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequestMapping(value = "/api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping(value = "/register")
    public ResponseEntity<UserDTO> registerUser(@RequestBody UserDTO userDTO) {
        HttpHeaders headers = new HttpHeaders();
        User savedUser = userService.saveUser(userDTO);// Guarda el usuario en el repositorio
        if (savedUser == null) {
            headers.add("Header", "FAIL");
            return new ResponseEntity<>(headers, HttpStatus.CONFLICT);
        }
        headers.add("Header", "OK");
        UserDTO userResponse = userService.convertToUserDTO(savedUser);
        return new ResponseEntity<>(userResponse, headers, HttpStatus.OK);
    }

    @PostMapping(value = "/login")
    public ResponseEntity<UserDTO> login(@RequestBody UserDTO userDTO) {
        User findUser = userService.findByUsername(userDTO.getUsername());
        HttpHeaders headers = new HttpHeaders();

        if (findUser != null && findUser.getPassword().equals(userDTO.getPassword())) {
            headers.add("Header", "OK");
            UserDTO response = userService.convertToUserDTO(findUser);
            return new ResponseEntity<>(response, headers, HttpStatus.OK);
        }
        headers.add("Header", "FAIL");
        return new ResponseEntity<>(headers, HttpStatus.NO_CONTENT);
    }

    @PutMapping(value = "/update-coin-balance/{userId}")
    public ResponseEntity<String> updateCoinBalance(
            @PathVariable Long userId,
            @RequestParam int newCoinBalance
    ) {
        HttpHeaders headers = new HttpHeaders();
        User updatedUser = userService.updateCoinBalance(userId, newCoinBalance);

        if (updatedUser != null) {
            headers.add("Header", "OK");
            return new ResponseEntity<>(headers, HttpStatus.OK);
        } else {
            headers.add("Header", "FAIL");
            return new ResponseEntity<>(headers, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(value = "/coin-balance/{userId}")
    public ResponseEntity<Integer> getCoinBalance(@PathVariable Long userId) {
        HttpHeaders headers = new HttpHeaders();
        int coinBalance = userService.getCoinBalance(userId);

        if (coinBalance >= 0) {
            headers.add("Header", "OK");
            return new ResponseEntity<>(coinBalance, headers, HttpStatus.OK);
        } else {
            headers.add("Header", "FAIL");
            return new ResponseEntity<>(coinBalance, headers, HttpStatus.NOT_FOUND);
        }
    }
    }



