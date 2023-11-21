package com.example.apiuplay.controllers;

import com.example.apiuplay.services.JwtService;
import com.example.apiuplay.models.entities.User;
import com.example.apiuplay.models.views.UserModifyPasswordDTO;
import com.example.apiuplay.models.views.UserDTO;
import com.example.apiuplay.models.views.UserRegistrationDTO;
import com.example.apiuplay.services.ResendService;
import com.example.apiuplay.services.UserService;
import org.apache.commons.lang3.ObjectUtils;
import org.jetbrains.annotations.NotNull;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(value = "/api/users")
public class UserController {

    private final UserService userService;
    private final ResendService resendService = new ResendService();

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping(value = "/register")
    public ResponseEntity<UserDTO> registerUser(@RequestBody UserRegistrationDTO userRegistrationDTO) {
        HttpHeaders headers = new HttpHeaders();
        User savedUser = userService.createUser(userRegistrationDTO);// Guarda el usuario en el repositorio
        if (savedUser == null) {
            headers.add("Header", "FAIL");
            return new ResponseEntity<>(headers, HttpStatus.CONFLICT);
        }
        headers.add("Header", "OK");
        this.resendService.sendMailRegister(savedUser.getName());
        UserDTO userResponse = userService.getBasicDataUserDTO(savedUser);
        return new ResponseEntity<>(userResponse, headers, HttpStatus.OK);
    }

    @PostMapping(value = "/login")
    public ResponseEntity<Map<String, String>> login(@RequestBody UserDTO userDTO) {
        User findUser = userService.findByUsername(userDTO.getUsername());
        if (ObjectUtils.isEmpty(findUser)) {
            findUser = userService.findByEmail(userDTO.getUsername());
        }
        HttpHeaders headers = new HttpHeaders();

        if (findUser != null && findUser.getPassword().equals(userDTO.getPassword())) {
            headers.add("Header", "OK");
            UserDTO response = userService.getBasicDataUserDTO(findUser);

            String token = JwtService.generateToken(response.getUsername());

            Map<String, String> responseBody = new HashMap<>();
            responseBody.put("token", token);
            return new ResponseEntity<>(responseBody, headers, HttpStatus.OK);
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

    @PutMapping("/update-user/{id}")
    public ResponseEntity<UserDTO> updateUser(@PathVariable Long id, @RequestBody UserDTO updatedUser) {
        HttpHeaders headers = new HttpHeaders();
        User existingUser = userService.findById(id);
        if (existingUser != null) {
            User savedUser = userService.saveUser(updatedUser, existingUser);
            return getUserDTOResponseEntity(headers, savedUser);
        } else {
            headers.add("Header", "FAIL");
            return new ResponseEntity<>(headers, HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/modifyPassword")
    public ResponseEntity<UserDTO> modifyPassword(@RequestBody UserModifyPasswordDTO userModifyPasswordDTO) {
        HttpHeaders headers = new HttpHeaders();
        User existingUser = userService.findByEmail(userModifyPasswordDTO.getEmail());
        if (ObjectUtils.isNotEmpty(existingUser)) {
            User updatedUser = userService.modifyPassword(userModifyPasswordDTO, existingUser);
            return getUserDTOResponseEntity(headers, updatedUser);
        } else {
            headers.add("Header", "FAIL");
            return new ResponseEntity<>(headers, HttpStatus.NOT_FOUND);
        }
    }

    @NotNull
    private ResponseEntity<UserDTO> getUserDTOResponseEntity(HttpHeaders headers, User savedUser) {
        if (ObjectUtils.isNotEmpty(savedUser)) {
            UserDTO userResponse = userService.getBasicDataUserDTO(savedUser);
            headers.add("Header", "OK");
            return new ResponseEntity<>(userResponse, headers, HttpStatus.OK);
        } else {
            headers.add("Header", "FAIL");
            return new ResponseEntity<>(headers, HttpStatus.NO_CONTENT);
        }
    }
}



